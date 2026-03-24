package design_problems.personal.due.ride_sharing_uber_ola.repository;

import design_problems.personal.due.ride_sharing_uber_ola.models.Ride;
import java.util.*;

public class RideRepository {

    Map<String, Ride> rides = new HashMap<>();

    public void save(Ride ride) {
        rides.put(ride.id, ride);
    }
}

