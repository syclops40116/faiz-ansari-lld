package design_problems.personal.parkinglot.model.vehicle;

import design_problems.personal.parkinglot.enums.VehicleType;

public class Car extends Vehicle {
    public Car(String vehicleNumber) {
        super(vehicleNumber, VehicleType.CAR);
    }
}
