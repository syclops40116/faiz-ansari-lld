package design_problems.personal.due.ride_sharing_uber_ola;

import design_problems.personal.due.ride_sharing_uber_ola.enums.ProductType;
import design_problems.personal.due.ride_sharing_uber_ola.models.Location;
import design_problems.personal.due.ride_sharing_uber_ola.products.Product;
import design_problems.personal.due.ride_sharing_uber_ola.products.ProductFactory;
import design_problems.personal.due.ride_sharing_uber_ola.service.FareEstimateService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        FareEstimateService fareEstimateService = new FareEstimateService();
        List<Product> products =
                Arrays.stream(ProductType.values())
                        .map(ProductFactory::create)   // Convert enum → Product
                        .toList();

        Location src = new Location(2, 2);
        Location dest = new Location(8, 8);
        // ==== Fare Estimates for the ride ====
        Map<Product, Double> fareEstimations = fareEstimateService.getFareEstimates(src, dest, products);
        for (Map.Entry<Product, Double> entry: fareEstimations.entrySet()) {
            System.out.println(entry.getKey() +" -> " +entry.getValue());
        }

        

    }
}
