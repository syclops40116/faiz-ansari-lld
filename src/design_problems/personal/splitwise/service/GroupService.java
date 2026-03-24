package design_problems.personal.splitwise.service;

import design_problems.personal.splitwise.enums.SplitType;
import design_problems.personal.splitwise.factory.SplitStrategyFactory;
import design_problems.personal.splitwise.model.Expense;
import design_problems.personal.splitwise.model.Group;
import design_problems.personal.splitwise.model.Split;
import design_problems.personal.splitwise.model.User;
import design_problems.personal.splitwise.repository.GroupRepository;
import design_problems.personal.splitwise.strategy.SplitStrategy;

import java.util.List;

public class GroupService {

    public GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group createGroup(String id, String name) {
        Group group = new Group(id, name);
        groupRepository.save(group);
        return group;
    }

    public void addUser(String groupId, User user) {
        Group group = groupRepository.getById(groupId);
        if(group == null) {
            throw new RuntimeException("Group not found");
        }
        group.addUser(user);
    }

    public void addExpense(String groupId, String expenseId, String description,
                           User paidBy, List<Split> splits, SplitType splitType, double amount) {
        Group group = groupRepository.getById(groupId);
        if(group == null) {
            throw new RuntimeException("Group not found");
        }

        SplitStrategy strategy = SplitStrategyFactory.createSplitStrategy(splitType);
        strategy.calculate(amount, splits);
        Expense expense = new Expense(expenseId, description, paidBy, amount, splits);
        group.addExpense(expense);
    }

    public void printBalances(String groupId) {
        Group group = groupRepository.getById(groupId);
        if(group == null) {
            throw new RuntimeException("Group not found");
        }

        group.printBalances();
    }
}
