package design_problems.personal.due.ride_sharing_uber_ola.matchingstrategy;

import design_problems.personal.due.ride_sharing_uber_ola.models.Driver;
import design_problems.personal.due.ride_sharing_uber_ola.models.Location;

import java.util.Comparator;
import java.util.List;

public class NearestDriverStrategy implements DriverMatchingStrategy{

    @Override
    public Driver findDriver(Location src, List<Driver> drivers) {
        drivers.sort(Comparator.comparing(driver -> driver.location.distanceTo(src)));
        for (Driver driver: drivers) {
            if(driver.markUnavailable()) {
                return driver;
            }
        }
        return null;
    }
}
