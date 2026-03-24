package design_problems.personal.bookmyshow.model;

import design_problems.personal.bookmyshow.enums.BookingStatus;
import design_problems.personal.bookmyshow.enums.PaymentType;

import java.util.List;

public class Booking {
    private String bookingId;
    private String showId;
    private String userId;
    private List<String> seatIds;
    private BookingStatus status;
    private PaymentType paymentType;
    private double amount;

    public Booking(String bookingId, String showId, String userId, List<String> seatIds, double amount) {
        this.bookingId = bookingId;
        this.showId = showId;
        this.userId = userId;
        this.seatIds = seatIds;
        this.amount = amount;
        this.status = BookingStatus.CREATED;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getShowId() {
        return showId;
    }

    public List<String> getSeatIds() {
        return seatIds;
    }

    public String getUserId() {
        return userId;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public double getAmount() {
        return amount;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }
}
