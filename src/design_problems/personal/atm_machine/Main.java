package design_problems.personal.atm_machine;

import design_problems.personal.atm_machine.model.ATMCard;
import design_problems.personal.atm_machine.model.BankAccount;
import design_problems.personal.atm_machine.service.ATMMachine;
import design_problems.personal.atm_machine.service.BankService;

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("bankAccount-1", "Faiz Ansari", 10000);
        ATMCard card = new ATMCard(account.accountId, "1234-5678-90", "1234");

        BankService bankService = BankService.getInstance();
        bankService.addAccount(account);

        ATMMachine atm = new ATMMachine();
        atm.loadCash(2000, 5);
        atm.loadCash(500, 5);
        atm.loadCash(100, 6);

        atm.insertCard(card);
        atm.enterPin("1234");
        atm.selectOption("WITHDRAW");
        atm.dispenseCash(2600);
    }
}
