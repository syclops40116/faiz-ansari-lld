package design_problems.personal.parkinglot.strategy.payment;

import design_problems.personal.parkinglot.model.Ticket;

public interface PaymentStrategy {
    boolean processPayment(Ticket ticket, double amount);
}
