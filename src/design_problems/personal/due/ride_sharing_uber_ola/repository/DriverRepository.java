package design_problems.personal.due.ride_sharing_uber_ola.repository;

import design_problems.personal.due.ride_sharing_uber_ola.models.Driver;
import design_problems.personal.due.ride_sharing_uber_ola.models.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverRepository {

    Map<String, Driver> drivers = new HashMap<>();

    public void save(Driver d) {
        drivers.put(d.id, d);
    }

    public List<Driver> findAll() {
        return new ArrayList<>(drivers.values());
    }

    public List<Driver> findNearByDrivers(Location location, double radius) {
        return findAll().stream()
                .filter(Driver::isAvailable)
                .filter(driver -> driver.location.distanceTo(location) <= radius)
                .toList();
    }
}
