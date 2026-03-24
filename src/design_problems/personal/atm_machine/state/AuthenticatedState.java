package design_problems.personal.atm_machine.state;

import design_problems.personal.atm_machine.model.ATMCard;
import design_problems.personal.atm_machine.service.ATMMachine;

public class AuthenticatedState implements ATMState{
    private final ATMMachine atm;

    public AuthenticatedState(ATMMachine atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard(ATMCard card) {
        System.out.println("Card already inserted.");
    }

    @Override
    public void enterPin(String pin) {
        System.out.println("Already authenticated.");
    }

    @Override
    public void selectOption(String option) {
        // can add options like deposit, check balance based on option selected.
        System.out.println("Option selected: Withdrawal.");
        atm.setState(new DispenseCashState(atm));
    }

    @Override
    public void dispenseCash(int amount) {
        System.out.println("Select an option first.");
    }

    @Override
    public void ejectCard() {
        atm.setCurrentCard(null);
        System.out.println("Card ejected.");
        atm.setState(new IdleState(atm));
    }
}
