package design_problems.personal.due.car_rental.strategy.booking;

import design_problems.personal.due.car_rental.model.Vehicle;

import java.util.List;

public interface BookingStrategy {
    Vehicle bookVehicle(List<Vehicle> vehicles);
}
