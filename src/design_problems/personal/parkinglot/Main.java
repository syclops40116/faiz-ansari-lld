package design_problems.personal.parkinglot;

import design_problems.personal.parkinglot.enums.PaymentType;
import design_problems.personal.parkinglot.enums.VehicleType;
import design_problems.personal.parkinglot.model.ParkingFloor;
import design_problems.personal.parkinglot.model.ParkingSpot;
import design_problems.personal.parkinglot.model.Ticket;
import design_problems.personal.parkinglot.model.gate.EntryGate;
import design_problems.personal.parkinglot.model.gate.ExitGate;
import design_problems.personal.parkinglot.model.vehicle.Bike;
import design_problems.personal.parkinglot.model.vehicle.Car;
import design_problems.personal.parkinglot.service.ParkingLot;

import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = ParkingLot.getInstance();
        EntryGate entryGate = new EntryGate("EG1", parkingLot);
        ExitGate exitGate = new ExitGate("XG1", parkingLot);

        ParkingFloor floor1 = new ParkingFloor("F1");
        floor1.addSpot(new ParkingSpot("S1", VehicleType.BIKE));
        floor1.addSpot(new ParkingSpot("S2", VehicleType.CAR));
        floor1.addSpot(new ParkingSpot("S3", VehicleType.CAR));
        floor1.addSpot(new ParkingSpot("S4", VehicleType.TRUCK));

        parkingLot.addFloor(floor1);

        Bike bike1 = new Bike("KA011234");
        Bike bike2 = new Bike("KA014567");

        Car car = new Car("KA-01-123456");

        AtomicReference<Ticket> ticketAtomicReference = new AtomicReference<>(null);

        Thread t1 = new Thread(() -> {
            ticketAtomicReference.set(entryGate.parkVehicle(car));
        });

//        Thread t2 = new Thread(() -> {
//            ticketAtomicReference.set(entryGate.parkVehicle(bike2));
//        });

//        t2.start();
        t1.start();

        try {
            t1.join();
//            t2.join();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Ticket ticket = ticketAtomicReference.get();

        if(ticket != null) {
            System.out.println(ticket.getVehicle().getVehicleNumber());
            long exitTime = ticketAtomicReference.get().getEntryTime() + (1000 * 60 * 45);
            exitGate.unparkVehicle(ticket.getTicketId(), exitTime, PaymentType.CARD);
        }
    }
}
