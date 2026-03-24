package design_problems.personal.atm_machine.model;

public class BankAccount {
    public String accountId;
    public String name;
    public int balance;

    public BankAccount(String accountId, String name, int balance) {
        this.accountId = accountId;
        this.name = name;
        this.balance = balance;
    }

    public void debit(int amount) {
        if(amount > balance) {
            throw new RuntimeException("Insufficient balance");
        }
        balance -= amount;
    }

    public int getBalance() {
        return this.balance;
    }
}
