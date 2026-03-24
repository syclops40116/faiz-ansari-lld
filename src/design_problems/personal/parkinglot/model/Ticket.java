package design_problems.personal.parkinglot.model;

import design_problems.personal.parkinglot.model.vehicle.Vehicle;

import java.util.UUID;

public class Ticket {
    private String ticketId;
    private long entryTime;
    private Vehicle vehicle;
    private String floorId;
    private String spotId;

    public Ticket(long entryTime, Vehicle vehicle, String floorId, String spotId) {
        this.ticketId = UUID.randomUUID().toString();
        this.entryTime = entryTime;
        this.vehicle = vehicle;
        this.floorId = floorId;
        this.spotId = spotId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getSpotId() {
        return spotId;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public String getFloorId() {
        return floorId;
    }
}
