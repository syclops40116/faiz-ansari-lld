package design_problems.personal.splitwise.model;

public class Split {
    public User user;
    public int percentage;
    public double amount;

    public Split(User user) {
        this.user = user;
        this.amount = amount;
    }

    public Split(User user, int percentage) {
        this.user = user;
        this.percentage = percentage;
    }
}
