package design_problems.personal.parkinglot.model.gate;

import design_problems.personal.parkinglot.service.ParkingLot;
import design_problems.personal.parkinglot.enums.GateType;

public class Gate {
    protected final String id;
    protected final ParkingLot parkingLot;
    protected final GateType gateType;

    public Gate(String id, ParkingLot parkingLot, GateType gateType) {
        this.id = id;
        this.parkingLot = parkingLot;
        this.gateType = gateType;
    }

    public String getId() {
        return id;
    }

    public GateType getGateType() {
        return gateType;
    }
}
