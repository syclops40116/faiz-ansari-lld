package design_problems.personal.parkinglot.service;

import design_problems.personal.parkinglot.enums.PaymentType;
import design_problems.personal.parkinglot.factory.PaymentStrategyFactory;
import design_problems.personal.parkinglot.model.ParkingFloor;
import design_problems.personal.parkinglot.model.ParkingSpot;
import design_problems.personal.parkinglot.model.Ticket;
import design_problems.personal.parkinglot.model.vehicle.Vehicle;
import design_problems.personal.parkinglot.strategy.payment.PaymentStrategy;
import design_problems.personal.parkinglot.strategy.pricing.HourlyPricingStrategy;
import design_problems.personal.parkinglot.strategy.pricing.PricingStrategy;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private static final ParkingLot INSTANCE = new ParkingLot();
    private PricingStrategy pricingStrategy;

    private final Map<String, ParkingFloor> floors = new HashMap<>();
    private final Map<String, Ticket> activeTickets = new HashMap<>();

    private ParkingLot() {
        this.pricingStrategy = new HourlyPricingStrategy();
    }

    public static ParkingLot getInstance() {
        return INSTANCE;
    }

    public void addFloor(ParkingFloor floor) {
        floors.put(floor.getId(), floor);
    }

    public Ticket parkVehicle(Vehicle vehicle, long entryTime) {
        for(ParkingFloor floor: floors.values()) {
            ParkingSpot spot = floor.findAvailableSpot(vehicle.getVehicleType());

            if(spot != null) {
                Ticket ticket = new Ticket(entryTime, vehicle, floor.getId(), spot.getId());
                activeTickets.put(ticket.getTicketId(), ticket);
                System.out.println("Vehicle parked. Ticket: " + ticket.getTicketId());
                return ticket;
            }
        }

        System.out.println("No spot available for vehicle type: " + vehicle.getVehicleType());
        return null;
    }

    public void unparkVehicle(String ticketId, long exitTime, PaymentType paymentType) {
        Ticket ticket = activeTickets.get(ticketId);
        if (ticket == null) {
            System.out.println("Invalid ticket ID.");
            return;
        }

        double fee = pricingStrategy.calculateFee(
                ticket.getVehicle().getVehicleType(),
                ticket.getEntryTime(),
                exitTime
        );

        PaymentStrategy strategy = PaymentStrategyFactory.get(paymentType);
        PaymentProcessor processor = new PaymentProcessor(strategy);
        boolean paid = processor.pay(ticket, fee);

        if (!paid) {
            System.out.println("Vehicle cannot exit. Payment unsuccessful.");
            return;
        }

        ParkingSpot spot = floors.get(ticket.getFloorId()).getSpots().get(ticket.getSpotId());
        spot.vacate();
        activeTickets.remove(ticketId);

        System.out.println("Vehicle exited. Fee charged: ₹" + fee);
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }
}
