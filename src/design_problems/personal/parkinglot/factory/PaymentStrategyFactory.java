package design_problems.personal.parkinglot.factory;

import design_problems.personal.parkinglot.enums.PaymentType;
import design_problems.personal.parkinglot.strategy.payment.CardPayment;
import design_problems.personal.parkinglot.strategy.payment.PaymentStrategy;
import design_problems.personal.parkinglot.strategy.payment.UpiPayment;

public class PaymentStrategyFactory {
    public static PaymentStrategy get(PaymentType type) {
        return switch (type) {
            case UPI -> new UpiPayment();
            case CARD -> new CardPayment();
        };
    }
}