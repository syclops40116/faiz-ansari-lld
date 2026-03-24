package design_problems.personal.splitwise.model;

import java.util.ArrayList;
import java.util.List;

public class Group {
    public String id;
    public String name;
    public List<User> members;
    public List<Expense> expenses;
    public BalanceSheet balanceSheet;

    public Group(String id, String name) {
        this.id = id;
        this.name = name;
        members = new ArrayList<>();
        expenses = new ArrayList<>();
        balanceSheet = new BalanceSheet();
    }

    public void addUser(User user) {
        balanceSheet.addUser(user);
        members.add(user);
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
        for(Split split: expense.splits) {
            if(split.user != expense.paidBy) {
                balanceSheet.update(expense.paidBy, split.user, split.amount);
            }
        }
    }


    public void printBalances() {
        balanceSheet.printSimplifiedDebts();
    }
}
