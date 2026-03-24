package design_problems.personal.due.car_rental.model;

import design_problems.personal.due.car_rental.enums.VehicleStatus;
import design_problems.personal.due.car_rental.enums.VehicleType;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Vehicle {
    private String licensePlate;
    private VehicleStatus status;
    private double pricePerHour;
    private double pricePerKm;
    private VehicleType type;
    private int bookingCount = 0;

    private final AtomicBoolean isBooked = new AtomicBoolean(false);

    public Vehicle(String licensePlate, double pricePerHour, double pricePerKm, VehicleType type) {
        this.licensePlate = licensePlate;
        this.status = VehicleStatus.AVAILABLE;
        this.pricePerHour = pricePerHour;
        this.pricePerKm = pricePerKm;
        this.type = type;
    }

    public void incrementBookingCount() {
        this.bookingCount++;
    }

    public AtomicBoolean getIsBooked() {
        return isBooked;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public VehicleType getType() {
        return type;
    }

    public int getBookingCount() {
        return bookingCount;
    }

    public double getPricePerKm() {
        return pricePerKm;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }

    public void setBookingCount(int bookingCount) {
        this.bookingCount = bookingCount;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public void setPricePerKm(double pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }
}
