package design_problems.personal.parkinglot.strategy.payment;

import design_problems.personal.parkinglot.model.Ticket;

public class UpiPayment implements PaymentStrategy {
    @Override
    public boolean processPayment(Ticket ticket, double amount) {
        System.out.println("Paid ₹" + amount + " for ticket " + ticket.getTicketId() + " via UPI.");
        return true;
    }
}