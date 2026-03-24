package design_problems.personal.due.splitwise.model;

import java.util.ArrayList;
import java.util.List;

public class Group {


    private final String id;
    private final String name;
    private final List<User> users = new ArrayList<>();
    private final List<Expense> expenses = new ArrayList<>();

    public Group(String id, String name) {
        this.id = id;
        this.name = name;
    }


    public String getId() { return id; }
    public String getName() { return name; }


    public List<User> getUsers() { return users; }
    public List<Expense> getExpenses() { return expenses; }


    public void addUser(User user) {
        users.add(user);
    }


    public void addExpense(Expense expense) {
        expenses.add(expense);
    }
}