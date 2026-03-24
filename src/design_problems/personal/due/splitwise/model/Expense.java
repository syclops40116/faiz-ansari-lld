package design_problems.personal.due.splitwise.model;

import java.util.List;

public class Expense {


    private final String id;
    private final User paidBy;
    private final double totalAmount;
    private final List<Split> splits;


    public Expense(String id, User paidBy, double totalAmount, List<Split> splits) {
        this.id = id;
        this.paidBy = paidBy;
        this.totalAmount = totalAmount;
        this.splits = splits;
    }


    public String getId() { return id; }
    public User getPaidBy() { return paidBy; }
    public double getTotalAmount() { return totalAmount; }
    public List<Split> getSplits() { return splits; }
}