package design_problems.personal.atm_machine.service;

import design_problems.personal.atm_machine.inventory.CashInventory;
import design_problems.personal.atm_machine.model.ATMCard;
import design_problems.personal.atm_machine.model.BankAccount;
import design_problems.personal.atm_machine.state.ATMState;
import design_problems.personal.atm_machine.state.IdleState;

public class ATMMachine {
    private ATMState state;
    private ATMCard currentCard;
    private final CashInventory cashInventory;
    private BankAccount linkedBankAccount;

    public ATMMachine() {
        cashInventory = new CashInventory();
        state = new IdleState(this);
    }

    public void insertCard(ATMCard card) {
        state.insertCard(card);
    }

    public void enterPin(String pin) {
        state.enterPin(pin);
    }

    public void selectOption(String option) {
        state.selectOption(option);
    }

    public void dispenseCash(int amount) {
        state.dispenseCash(amount);
    }

    public void loadCash(int denomination, int count) {
        cashInventory.loadCash(denomination, count);
    }

    public void setCurrentCard(ATMCard card) {
        this.currentCard = card;
    }

    public void setState(ATMState state) {
        this.state = state;
    }

    public void setLinkedBankAccount(BankAccount account) {
        this.linkedBankAccount = account;
    }

    public BankAccount getLinkedBankAccount() {
        return linkedBankAccount;
    }

    public ATMState getState() {
        return this.state;
    }

    public ATMCard getCurrentCard() {
        return this.currentCard;
    }

    public CashInventory getCashInventory() {
        return this.cashInventory;
    }
}
