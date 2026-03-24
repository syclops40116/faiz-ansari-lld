package design_problems.personal.parkinglot.model.vehicle;

import design_problems.personal.parkinglot.enums.VehicleType;

public class Bike extends Vehicle {
    public Bike(String vehicleNumber) {
        super(vehicleNumber, VehicleType.BIKE);
    }
}
