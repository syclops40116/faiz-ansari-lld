package design_problems.personal.due.car_rental.strategy.payment;

import design_problems.personal.due.car_rental.model.Booking;

public interface PaymentStrategy {
    boolean processPayment(Booking booking);
}
