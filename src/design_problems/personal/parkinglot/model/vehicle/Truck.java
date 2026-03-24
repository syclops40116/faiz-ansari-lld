package design_problems.personal.parkinglot.model.vehicle;

import design_problems.personal.parkinglot.enums.VehicleType;

public class Truck extends Vehicle {
    public Truck(String vehicleNumber) {
        super(vehicleNumber, VehicleType.TRUCK);
    }
}
