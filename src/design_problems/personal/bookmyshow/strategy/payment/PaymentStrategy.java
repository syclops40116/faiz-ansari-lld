package design_problems.personal.bookmyshow.strategy.payment;

import design_problems.personal.bookmyshow.model.Booking;

public interface PaymentStrategy {
    void pay(Booking booking, double amount);
}
