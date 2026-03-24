package design_problems.personal.parkinglot.strategy.pricing;

import design_problems.personal.parkinglot.enums.VehicleType;

import java.util.HashMap;
import java.util.Map;

public class HourlyPricingStrategy implements PricingStrategy{

    private final Map<VehicleType, Double> hourlyRates = new HashMap<>();

    public HourlyPricingStrategy() {
        hourlyRates.put(VehicleType.BIKE, 30.0);
        hourlyRates.put(VehicleType.CAR, 50.0);
        hourlyRates.put(VehicleType.TRUCK, 100.0);
    }

    @Override
    public double calculateFee(VehicleType type, long entryTime, long exitTime) {

        if (!hourlyRates.containsKey(type)) {
            throw new IllegalArgumentException("Invalid vehicle type: " + type);
        }

        if (exitTime < entryTime) {
            throw new IllegalArgumentException("Exit time cannot be before entry time");
        }

        // Calculate duration in milliseconds
        long durationMillis = exitTime - entryTime;

        // Convert to hours (round up to next hour)
        int hours = (int) Math.ceil(durationMillis / (1000.0 * 60 * 60));

        System.out.println("Calculating fee for " + hours + " hours");

        double ratePerHour = hourlyRates.get(type);

        return hours * ratePerHour;
    }
}
