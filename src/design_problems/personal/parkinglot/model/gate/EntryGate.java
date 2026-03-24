package design_problems.personal.parkinglot.model.gate;

import design_problems.personal.parkinglot.model.Ticket;
import design_problems.personal.parkinglot.model.vehicle.Vehicle;
import design_problems.personal.parkinglot.service.ParkingLot;
import design_problems.personal.parkinglot.enums.GateType;

public class EntryGate extends Gate{
    public EntryGate(String id, ParkingLot parkingLot) {
        super(id, parkingLot, GateType.ENTRY);
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        return parkingLot.parkVehicle(vehicle, System.currentTimeMillis());
    }
}
