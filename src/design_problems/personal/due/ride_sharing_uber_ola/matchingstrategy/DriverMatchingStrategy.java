package design_problems.personal.due.ride_sharing_uber_ola.matchingstrategy;

import design_problems.personal.due.ride_sharing_uber_ola.models.Driver;
import design_problems.personal.due.ride_sharing_uber_ola.models.Location;

import java.util.List;

public interface DriverMatchingStrategy {
    Driver findDriver(Location src, List<Driver> drivers);
}
