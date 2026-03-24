package design_problems.personal.parkinglot.model.gate;

import design_problems.personal.parkinglot.enums.PaymentType;
import design_problems.personal.parkinglot.service.ParkingLot;
import design_problems.personal.parkinglot.enums.GateType;

public class ExitGate extends Gate{
    public ExitGate(String id, ParkingLot parkingLot) {
        super(id, parkingLot, GateType.EXIT);
    }

    public void unparkVehicle(String ticketId, long exitTime, PaymentType paymentType) {
        parkingLot.unparkVehicle(ticketId, exitTime, paymentType);
    }
}
