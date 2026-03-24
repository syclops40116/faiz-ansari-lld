package design_problems.personal.due.ride_sharing_uber_ola.models;

import design_problems.personal.due.ride_sharing_uber_ola.enums.RideStatus;

public class Ride {

    public String id;
    public Rider rider;
    public Driver driver;
    public Location source;
    public Location destination;
    public double fare;
    public RideStatus status;

    public Ride(String id, Rider rider, Location source, Location destination) {
        this.id = id;
        this.rider = rider;
        this.source = source;
        this.destination = destination;
        this.status = RideStatus.REQUESTED;
    }
}
