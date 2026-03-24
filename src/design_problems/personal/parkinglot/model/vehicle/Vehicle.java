package design_problems.personal.parkinglot.model.vehicle;

import design_problems.personal.parkinglot.enums.VehicleType;

public abstract class Vehicle {
    private final String vehicleNumber;
    private final VehicleType vehicleType;

    public Vehicle(String vehicleNumber, VehicleType vehicleType) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
