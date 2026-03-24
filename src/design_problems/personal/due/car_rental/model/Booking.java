package design_problems.personal.due.car_rental.model;

import design_problems.personal.due.car_rental.enums.BookingStatus;
import design_problems.personal.due.car_rental.enums.PaymentStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Booking {
    private String bookingId;
    private User user;
    private Vehicle vehicle;
    private Branch pickupBranch;
    private Branch dropBranch;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BookingStatus status = BookingStatus.CREATED;
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;
    private double amount;

    // Private constructor (only Builder can create)
    private Booking(Builder builder) {

        this.bookingId = builder.bookingId;
        this.user = builder.user;
        this.vehicle = builder.vehicle;
        this.pickupBranch = builder.pickupBranch;
        this.dropBranch = builder.dropBranch;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;

        this.status = builder.status;
        this.paymentStatus = builder.paymentStatus;
        this.amount = builder.amount;
    }

    // Static Builder Entry Point
    public static Builder builder() {
        return new Builder();
    }


    // -------- BUILDER --------
    public static class Builder {

        private String bookingId;
        private User user;
        private Vehicle vehicle;
        private Branch pickupBranch;
        private Branch dropBranch;
        private LocalDateTime startTime;
        private LocalDateTime endTime;

        private BookingStatus status = BookingStatus.CREATED;
        private PaymentStatus paymentStatus = PaymentStatus.PENDING;

        private double amount;

        // Required fields
        public Builder() {
        }

        public Builder bookingId(String bookingId) {
            this.bookingId = bookingId;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder vehicle(Vehicle vehicle) {
            this.vehicle = vehicle;
            return this;
        }

        public Builder pickupBranch(Branch branch) {
            this.pickupBranch = branch;
            return this;
        }

        public Builder dropBranch(Branch branch) {
            this.dropBranch = branch;
            return this;
        }

        public Builder startTime(LocalDateTime time) {
            this.startTime = time;
            return this;
        }

        public Builder endTime(LocalDateTime time) {
            this.endTime = time;
            return this;
        }

        public Builder status(BookingStatus status) {
            this.status = status;
            return this;
        }

        public Builder paymentStatus(PaymentStatus status) {
            this.paymentStatus = status;
            return this;
        }

        public Builder amount(double amount) {
            this.amount = amount;
            return this;
        }

        // Validation + Build
        public Booking build() {

            if (startTime == null || endTime == null) {
                throw new IllegalStateException("Start and End time required");
            }

            if (endTime.isBefore(startTime)) {
                throw new IllegalStateException("End time before start time");
            }

            return new Booking(this);
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM h:mm a yyyy", Locale.ENGLISH);
        return "\n" +
                "Booking ID: " + bookingId + "\n" +
                "User: " + (user != null ? user.getName() : "N/A") + "\n" +
                "Pickup Time: " + startTime.format(formatter) + "\n" +
                "Drop Time: " + endTime.format(formatter) + "\n" +
                "Pickup Location: " + (pickupBranch != null ? pickupBranch.getCity() : "N/A") + "\n" +
                "Drop Location: " + (dropBranch != null ? dropBranch.getCity() : "N/A") + "\n" +
                "Vehicle Type: " + (vehicle != null ? vehicle.getType() : "N/A") + "\n" +
                "Vehicle Number Plate: " + (vehicle != null ? vehicle.getLicensePlate() : "N/A") + "\n" +
                "Amount: $" + String.format("%.2f", amount) + "\n" +
                "Status: " + status + "\n";
    }

    public String getBookingId() {
        return bookingId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public Branch getDropBranch() {
        return dropBranch;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}