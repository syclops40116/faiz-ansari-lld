package design_problems.personal.parkinglot.strategy.pricing;

import design_problems.personal.parkinglot.enums.VehicleType;

import java.time.LocalDateTime;

public interface PricingStrategy {
    double calculateFee(VehicleType type, long entryTime, long exitTime);
}
