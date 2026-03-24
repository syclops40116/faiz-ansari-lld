package design_problems.personal.atm_machine.state;

import design_problems.personal.atm_machine.model.ATMCard;
import design_problems.personal.atm_machine.service.ATMMachine;
import design_problems.personal.atm_machine.service.BankService;

public class IdleState implements ATMState{

    ATMMachine atm;
    BankService bankService = BankService.getInstance();

    public IdleState(ATMMachine atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard(ATMCard card) {
        atm.setCurrentCard(card);

        atm.setLinkedBankAccount(bankService.getAccountWithAccountId(card.accountId));

        System.out.println("Card inserted.");
        atm.setState(new CardInsertedState(atm));
    }

    @Override
    public void enterPin(String pin) {
        System.out.println("No card inserted.");
    }

    @Override
    public void selectOption(String option) {
        System.out.println("No card inserted.");
    }

    @Override
    public void dispenseCash(int amount) {
        System.out.println("No card inserted.");
    }

    @Override
    public void ejectCard() {
        System.out.println("No card inserted.");
    }
}
