package design_problems.personal.due.splitwise.service;

import design_problems.personal.due.splitwise.model.User;
import design_problems.personal.due.splitwise.model.BalanceSheet;
import design_problems.personal.due.splitwise.model.Expense;
import design_problems.personal.due.splitwise.model.Group;
import design_problems.personal.due.splitwise.model.Split;
import design_problems.personal.due.splitwise.repository.ExpenseRepository;
import design_problems.personal.due.splitwise.repository.GroupRepository;
import design_problems.personal.due.splitwise.strategy.SplitStrategy;

import java.util.Map;

public class GroupService {
    private final GroupRepository groupRepo;
    private final ExpenseRepository expenseRepo;
    private final BalanceSheet balanceSheet = new BalanceSheet();


    public GroupService(GroupRepository gRepo, ExpenseRepository eRepo) {
        this.groupRepo = gRepo;
        this.expenseRepo = eRepo;
    }


    public Group createGroup(String id, String name) {
        Group g = new Group(id, name);
        groupRepo.save(g);
        return g;
    }


    public void addUser(String groupId, User user) {
        Group g = groupRepo.findById(groupId);
        g.addUser(user);
        balanceSheet.addUser(user);
    }


    public void addExpense(String groupId,
                           Expense expense,
                           SplitStrategy strategy) {


        strategy.calculate(expense.getTotalAmount(), expense.getSplits());


        Group g = groupRepo.findById(groupId);
        g.addExpense(expense);
        expenseRepo.save(groupId, expense);


        User paidBy = expense.getPaidBy();


        for (Split s : expense.getSplits()) {


            if (!s.getUser().getId().equals(paidBy.getId())) {
                balanceSheet.update(s.getUser(), paidBy, s.getAmount());
            }
        }
    }


    public void showBalances() {


        for (String u1 : balanceSheet.getBalances().keySet()) {
            for (Map.Entry<String, Double> e :
                    balanceSheet.getBalances().get(u1).entrySet()) {


                if (e.getValue() > 0) {
                    System.out.println(u1 + " owes " + e.getKey() + " Rs." + e.getValue());
                }
            }
        }
    }


    public void simplify() {
        System.out.println("\n--- Simplified Debts ---");


        for (String s : balanceSheet.simplify()) {
            System.out.println(s);
        }
    }
}