package design_problems.personal.due.ride_sharing_uber_ola.repository;

import design_problems.personal.due.ride_sharing_uber_ola.models.Rider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RiderRepository {

    private final Map<String, Rider> riders = new HashMap<>();

    public void save(Rider rider) {
        riders.put(rider.id, rider);
    }

    public Rider findById(String id) {
        return riders.get(id);
    }

    public List<Rider> findAll() {
        return new ArrayList<>(riders.values());
    }
}

