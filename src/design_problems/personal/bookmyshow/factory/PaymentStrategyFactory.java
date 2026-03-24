package design_problems.personal.bookmyshow.factory;

import design_problems.personal.bookmyshow.enums.PaymentType;
import design_problems.personal.bookmyshow.strategy.payment.CardPaymentStrategy;
import design_problems.personal.bookmyshow.strategy.payment.PaymentStrategy;
import design_problems.personal.bookmyshow.strategy.payment.UPIPaymentStrategy;

public class PaymentStrategyFactory {

    public static PaymentStrategy createPaymentStrategy(PaymentType paymentType) {
        switch (paymentType) {
            case UPI -> {
                return new UPIPaymentStrategy();
            }
            case CARD -> {
                return new CardPaymentStrategy();
            }
            default -> throw new RuntimeException("Invalid Payment Type");
        }
    }
}
