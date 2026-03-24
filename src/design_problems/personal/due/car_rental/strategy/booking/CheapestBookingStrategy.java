package design_problems.personal.due.car_rental.strategy.booking;

import design_problems.personal.due.car_rental.enums.PricingStrategyType;
import design_problems.personal.due.car_rental.model.Vehicle;

import java.util.List;

public class CheapestBookingStrategy implements BookingStrategy {
    private final PricingStrategyType pricingType;

    public CheapestBookingStrategy(PricingStrategyType pricingType) {
        this.pricingType = pricingType;
    }

    @Override
    public Vehicle bookVehicle(List<Vehicle> vehicles) {
        // Sort vehicles by price based on pricingType (time or distance)
        List<Vehicle> sortedVehicles = vehicles.stream()
                .sorted((v1, v2) -> {
                    double val1 = pricingType == PricingStrategyType.TIME_BASED ? v1.getPricePerHour() : v1.getPricePerKm();
                    double val2 = pricingType == PricingStrategyType.TIME_BASED ? v2.getPricePerHour() : v2.getPricePerKm();
                    return Double.compare(val1, val2);
                })
                .toList();

        // Try booking each vehicle atomically
        for (Vehicle vehicle : sortedVehicles) {
            // t1, t2 (1th index)
            if (vehicle.getIsBooked().compareAndSet(false, true)) {
                return vehicle;
            }
        }
        return null;
    }
}
