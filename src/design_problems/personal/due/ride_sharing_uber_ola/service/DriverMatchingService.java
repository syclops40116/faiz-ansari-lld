package design_problems.personal.due.ride_sharing_uber_ola.service;

import design_problems.personal.due.ride_sharing_uber_ola.matchingstrategy.DriverMatchingStrategy;
import design_problems.personal.due.ride_sharing_uber_ola.models.Driver;
import design_problems.personal.due.ride_sharing_uber_ola.models.Location;
import design_problems.personal.due.ride_sharing_uber_ola.products.Product;
import design_problems.personal.due.ride_sharing_uber_ola.repository.DriverRepository;

import java.util.List;

public class DriverMatchingService {
    private final DriverRepository driverRepository;
    private final DriverMatchingStrategy strategy;

    public DriverMatchingService(DriverRepository driverRepository, DriverMatchingStrategy strategy) {
        this.driverRepository = driverRepository;
        this.strategy = strategy;
    }

    public Driver findDriver(Location src, Product product) {
        List<Driver> nearByDrivers = driverRepository.findNearByDrivers(src, 5);

        // Get drivers that support the selected product
        List<Driver> filtered = nearByDrivers.stream()
                .filter(driver -> driver.vehicle != null && driver.vehicle.supportedProducts.contains(product))
                .toList();

        return strategy.findDriver(src, filtered);
    }
}
