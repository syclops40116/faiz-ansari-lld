package design_problems.personal.due.splitwise;

import design_problems.personal.due.splitwise.controller.SplitwiseController;
import design_problems.personal.due.splitwise.model.Group;
import design_problems.personal.due.splitwise.model.User;
import design_problems.personal.due.splitwise.repository.ExpenseRepository;
import design_problems.personal.due.splitwise.repository.GroupRepository;
import design_problems.personal.due.splitwise.repository.UserRepository;
import design_problems.personal.due.splitwise.service.GroupService;
import design_problems.personal.due.splitwise.service.UserService;
import design_problems.personal.due.splitwise.strategy.EqualSplitStrategy;
import design_problems.personal.due.splitwise.strategy.PercentageSplitStrategy;

import java.util.Arrays;
import java.util.List;

public class Main {


    public static void main(String[] args) {


        // Repositories
        UserRepository userRepo = new UserRepository();
        GroupRepository groupRepo = new GroupRepository();
        ExpenseRepository expenseRepo = new ExpenseRepository();


        // Services
        UserService userService = new UserService(userRepo);
        GroupService groupService = new GroupService(groupRepo, expenseRepo);


        // Controller
        SplitwiseController controller =
                new SplitwiseController(userService, groupService);


        // Users
        User u1 = controller.createUser("U1", "Aman");
        User u2 = controller.createUser("U2", "Rohit");
        User u3 = controller.createUser("U3", "Neha");


        // Group
        Group trip = controller.createGroup("G1", "Goa Trip");


        controller.addUserToGroup("G1", u1);
        controller.addUserToGroup("G1", u2);
        controller.addUserToGroup("G1", u3);


//        // Equal Expense
//        controller.addExpense(
//                "G1",
//                "E1",
//                u1,
//                3000,
//                Arrays.asList(u1, u2, u3),
//                new EqualSplitStrategy()
//        );
//
//
//        // Percentage Expense
//        controller.addExpense(
//                "G1",
//                "E2",
//                u2,
//                2000,
//                Arrays.asList(u1, u2, u3),
//                new PercentageSplitStrategy(Arrays.asList(50.0, 30.0, 20.0))
//        );

        controller.addExpense(
                "G1",
                "E1", u1,
                150, List.of(u1, u2, u3),
                new EqualSplitStrategy());

        controller.addExpense(
                "G1",
                "E1", u2,
                150, List.of(u1, u2, u3),
                new EqualSplitStrategy());

        Group group2 = controller.createGroup("G2", "Group 2");
        User u4 = controller.createUser("u4", "U4");
        User u5 = controller.createUser("u5", "U5");
        User u6 = controller.createUser("u6", "U6");


        controller.addExpense(
                "G2",
                "E3", u4,
                150, List.of(u4, u5, u6),
                new EqualSplitStrategy());

        controller.addExpense(
                "G2",
                "E4", u4,
                150, List.of(u4, u5, u6),
                new EqualSplitStrategy());

        // View Output
        System.out.println("--- Balances ---");
        controller.showBalances();



//        controller.simplify();
    }
}