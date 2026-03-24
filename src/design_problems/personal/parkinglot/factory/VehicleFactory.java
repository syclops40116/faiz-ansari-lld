package design_problems.personal.parkinglot.factory;

import design_problems.personal.parkinglot.enums.VehicleType;
import design_problems.personal.parkinglot.model.vehicle.Bike;
import design_problems.personal.parkinglot.model.vehicle.Car;
import design_problems.personal.parkinglot.model.vehicle.Truck;
import design_problems.personal.parkinglot.model.vehicle.Vehicle;

public class VehicleFactory {
    public static Vehicle create(String number, VehicleType type) {
        return switch (type) {
            case CAR -> new Car(number);
            case BIKE -> new Bike(number);
            case TRUCK -> new Truck(number);
        };
    }
}

