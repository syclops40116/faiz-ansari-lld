package design_problems.personal.due.ride_sharing_uber_ola.models;

import java.util.concurrent.atomic.AtomicBoolean;

public class Driver {

    public String id;
    public String name;
    public Location location;
    public AtomicBoolean available;
    public Vehicle vehicle;

    public Driver(String id, String name, Location location, Vehicle vehicle) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.vehicle = vehicle;
        this.available = new AtomicBoolean(true);
    }

    public boolean isAvailable() {
        return available.get();
    }

    public boolean markUnavailable() {
        return available.compareAndSet(true, false);
    }
}
