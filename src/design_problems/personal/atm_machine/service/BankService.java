package design_problems.personal.atm_machine.service;

import design_problems.personal.atm_machine.model.BankAccount;

import java.util.HashMap;
import java.util.Map;

public class BankService {

    private static final BankService INSTANCE = new BankService();

    private final Map<String, BankAccount> accounts;

    private BankService() {accounts = new HashMap<>();}

    public static BankService getInstance() {
        return INSTANCE;
    }

    public void addAccount(BankAccount account) {
        accounts.put(account.accountId, account);
    }

    public BankAccount getAccountWithAccountId(String accountId) {
        BankAccount bankAccount = accounts.getOrDefault(accountId, null);
        if(bankAccount == null) {
            throw new RuntimeException("Account not found");
        }
        return bankAccount;
    }

    public int getBalanceWithAccountId(String accountId) {
        BankAccount bankAccount = accounts.getOrDefault(accountId, null);
        if(bankAccount == null) {
            throw new RuntimeException("Account not found");
        }
        return bankAccount.balance;
    }

    public void debit(String accountId, int amount) {
        BankAccount bankAccount = accounts.getOrDefault(accountId, null);
        if(bankAccount == null) {
            throw new RuntimeException("Account not found");
        }
        bankAccount.debit(amount);
    }
}
