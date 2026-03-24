package design_problems.personal.due.ride_sharing_uber_ola.service;

import design_problems.personal.due.ride_sharing_uber_ola.models.Rider;
import design_problems.personal.due.ride_sharing_uber_ola.repository.RiderRepository;

import java.util.UUID;

public class RiderService {

    private final RiderRepository riderRepo;

    public RiderService(RiderRepository riderRepo) {
        this.riderRepo = riderRepo;
    }

    public void registerRider(String name) {
        riderRepo.save(new Rider(UUID.randomUUID().toString() + name, name));
    }

    public Rider getRider(String id) {
        return riderRepo.findById(id);
    }
}
