package design_problems.personal.due.splitwise.controller;

import design_problems.personal.due.splitwise.model.User;
import design_problems.personal.due.splitwise.model.Expense;
import design_problems.personal.due.splitwise.model.Group;
import design_problems.personal.due.splitwise.model.Split;
import design_problems.personal.due.splitwise.service.GroupService;
import design_problems.personal.due.splitwise.service.UserService;
import design_problems.personal.due.splitwise.strategy.SplitStrategy;

import java.util.ArrayList;
import java.util.List;

public class SplitwiseController {
    private final GroupService groupService;
    private final UserService userService;

    public SplitwiseController(UserService userService, GroupService groupService) {
        this.userService = userService;
        this.groupService = groupService;
    }


    public User createUser(String id, String name) {
        return userService.createUser(id, name);
    }


    public Group createGroup(String id, String name) {
        return groupService.createGroup(id, name);
    }


    public void addUserToGroup(String groupId, User user) {
        groupService.addUser(groupId, user);
    }


    public void addExpense(String groupId,
                           String expenseId,
                           User paidBy,
                           double amount,
                           List<User> users,
                           SplitStrategy strategy) {


        List<Split> splits = new ArrayList<>();


        for (User u : users) {
            splits.add(new Split(u));
        }


        Expense e = new Expense(expenseId, paidBy, amount, splits);


        groupService.addExpense(groupId, e, strategy);
    }


    public void showBalances() {
        groupService.showBalances();
    }


    public void simplify() {
        groupService.simplify();
    }
}
