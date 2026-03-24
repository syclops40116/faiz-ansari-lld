package design_problems.personal.splitwise.model;

import java.util.List;

public class Expense {
    public String id;
    public String description;
    public User paidBy;
    public double amount;
    public List<Split> splits;

    public Expense(String id, String description, User paidBy, double amount, List<Split> splits) {
        this.paidBy = paidBy;
        this.description = description;
        this.amount = amount;
        this.id = id;
        this.splits = splits;
    }
}
