package design_problems.personal.due.car_rental.strategy.payment;

import design_problems.personal.due.car_rental.model.Booking;

public class WalletPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean processPayment(Booking booking) {
        // Simulate wallet payment processing
        System.out.println("Processing wallet payment for booking: " + booking.getBookingId());
        return true;
    }
}
