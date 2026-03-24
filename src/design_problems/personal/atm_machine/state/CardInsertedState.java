package design_problems.personal.atm_machine.state;

import design_problems.personal.atm_machine.model.ATMCard;
import design_problems.personal.atm_machine.service.ATMMachine;

public class CardInsertedState implements ATMState{
    private final ATMMachine atm;

    public CardInsertedState(ATMMachine atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard(ATMCard card) {
        System.out.println("Card already inserted.");
    }

    @Override
    public void enterPin(String pin) {
        if (atm.getCurrentCard().pin.equals(pin)) {
            System.out.println("PIN correct. Authenticated.");
            atm.setState(new AuthenticatedState(atm));
        } else {
            System.out.println("Invalid PIN.");
            ejectCard();
        }
    }

    @Override
    public void selectOption(String option) {
        System.out.println("Enter PIN first.");
    }

    @Override
    public void dispenseCash(int amount) {
        System.out.println("Enter PIN before dispensing.");
    }

    @Override
    public void ejectCard() {
        atm.setCurrentCard(null);
        System.out.println("Card ejected.");
        atm.setState(new IdleState(atm));
    }
}
