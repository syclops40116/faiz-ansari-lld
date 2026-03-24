package design_problems.personal.atm_machine.state;

import design_problems.personal.atm_machine.inventory.CashInventory;
import design_problems.personal.atm_machine.model.ATMCard;
import design_problems.personal.atm_machine.service.ATMMachine;

public class DispenseCashState implements ATMState{

    private final ATMMachine atm;

    public DispenseCashState(ATMMachine atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard(ATMCard card) {
        System.out.println("Transaction in progress. Cannot insert another card.");
    }

    @Override
    public void enterPin(String pin) {
        System.out.println("Already authenticated.");
    }

    @Override
    public void selectOption(String option) {
        System.out.println("Option already selected.");
    }

    @Override
    public void dispenseCash(int amount) {
        CashInventory cashInventory = atm.getCashInventory();

        if(amount > atm.getLinkedBankAccount().getBalance()) {
            System.out.println("Insufficient Balance");
        }

        if(amount > cashInventory.getAvailableCash()) {
            System.out.println("ATM has insufficient cash");
        }

        if(cashInventory.canDispense(amount)) {
            System.out.println("Cannot dispense requested amount with available denominations.");
        }

        cashInventory.dispenseAmount(amount);
        atm.getLinkedBankAccount().debit(amount);
        ejectCard();
    }

    @Override
    public void ejectCard() {
        atm.setCurrentCard(null);
        System.out.println("Card ejected.");
        atm.setState(new IdleState(atm)); // use factory
    }
}
