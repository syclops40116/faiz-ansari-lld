package design_problems.personal.atm_machine.model;

public class ATMCard {
    public String accountId;
    public String cardNumber;
    public String pin;

    public ATMCard(String accountId, String cardNumber, String pin) {
        this.cardNumber = cardNumber;
        this.accountId = accountId;
        this.pin = pin;
    }
}
