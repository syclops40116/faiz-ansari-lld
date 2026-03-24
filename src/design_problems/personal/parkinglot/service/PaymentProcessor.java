package design_problems.personal.parkinglot.service;

import design_problems.personal.parkinglot.model.Ticket;
import design_problems.personal.parkinglot.strategy.payment.PaymentStrategy;

public class PaymentProcessor {
    private final PaymentStrategy strategy;

    public PaymentProcessor(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean pay(Ticket ticket, double amount) {
        return strategy.processPayment(ticket, amount);
    }
}