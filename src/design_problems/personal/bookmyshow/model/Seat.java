package design_problems.personal.bookmyshow.model;

import design_problems.personal.bookmyshow.enums.SeatType;

public class Seat {
    private String id;
    private double price;
    private SeatType seatType;

    public Seat(String id, SeatType seatType, double price) {
        this.id = id;
        this.seatType = seatType;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public SeatType getSeatType() {
        return seatType;
    }
}
