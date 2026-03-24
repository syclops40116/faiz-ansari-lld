package design_problems.personal.bookmyshow.strategy.payment;

import design_problems.personal.bookmyshow.model.Booking;

public class UPIPaymentStrategy implements PaymentStrategy{
    @Override
    public void pay(Booking booking, double amount) {
        System.out.println("Paid " + amount + " via UPI for booking id: " + booking.getBookingId());
    }
}
