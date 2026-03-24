package design_problems.personal.due.car_rental.factory;

import design_problems.personal.due.car_rental.enums.VehicleType;
import design_problems.personal.due.car_rental.model.SUV;
import design_problems.personal.due.car_rental.model.Sedan;
import design_problems.personal.due.car_rental.model.Vehicle;

public class VehicleFactory {
    public static Vehicle create(VehicleType type, String licensePlate, double pricePerHour, double pricePerKm) {
        return switch (type) {
            case SEDAN -> new Sedan(licensePlate, pricePerHour, pricePerKm);
            case SUV -> new SUV(licensePlate, pricePerHour, pricePerKm);
        };
    }
}
