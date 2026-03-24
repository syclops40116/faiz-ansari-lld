package design_problems.personal.due.ride_sharing_uber_ola.service;

import design_problems.personal.due.ride_sharing_uber_ola.models.Location;
import design_problems.personal.due.ride_sharing_uber_ola.products.Product;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FareEstimateService {

    public Map<Product, Double> getFareEstimates(Location src, Location dest, List<Product> products) {
        Map<Product, Double> fareEstimates = new HashMap<>();

        for (Product product: products) {
            double roundedFare = Math.round(calculateFare(src, dest, product) * 100.0) / 100.0;
            fareEstimates.put(product, roundedFare);
        }
        return fareEstimates;
    }

    private double calculateFare(Location src, Location dest, Product product) {
        double distance = src.distanceTo(dest);

        double fare = product.getBaseFare() + distance * product.getPerKmRate();
        return fare * getSurgeMultiplier();
    }

    private double getSurgeMultiplier() {
        LocalTime now = LocalTime.now();

        // night surge
        if (now.isAfter(LocalTime.of(23, 0)) ||
                now.isBefore(LocalTime.of(5, 0))) {
            return 1.5;
        }
        // Peak hours
        else if ((now.isAfter(LocalTime.of(8, 0)) &&
                now.isBefore(LocalTime.of(10, 0))) ||

                (now.isAfter(LocalTime.of(18, 0)) &&
                        now.isBefore(LocalTime.of(21, 0)))) {
            return 1.2;
        }
        return 1.0;
    }
}
