package design_problems.personal.atm_machine.state;

import design_problems.personal.atm_machine.model.ATMCard;

public interface ATMState {
    void insertCard(ATMCard card);
    void enterPin(String pin);
    void selectOption(String option);
    void dispenseCash(int amount);
    void ejectCard();
}
