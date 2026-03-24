package design_problems.personal.splitwise;

import design_problems.personal.splitwise.enums.SplitType;
import design_problems.personal.splitwise.model.Group;
import design_problems.personal.splitwise.model.Split;
import design_problems.personal.splitwise.model.User;
import design_problems.personal.splitwise.repository.GroupRepository;
import design_problems.personal.splitwise.service.GroupService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        GroupRepository groupRepository = new GroupRepository();

        GroupService groupService = new GroupService(groupRepository);

        Group group = groupService.createGroup("G1", "Goa Trip");
        User faiz = new User("u1", "Faiz");
        User zaid = new User("u2", "Zaid");
        User junaid = new User("u3", "Junaid");


        groupService.addUser("G1", faiz);
        groupService.addUser("G1", zaid);
        groupService.addUser("G1", junaid);

        groupService.addExpense(
                "G1",
                "E1",
                "Flight",
                faiz,
                List.of(new Split(faiz), new Split(zaid), new Split(junaid)),
                SplitType.EQUAL,
                1500);

        groupService.addExpense(
                "G1",
                "E2",
                "Lunch",
                zaid,
                List.of(new Split(faiz), new Split(zaid), new Split(junaid)),
                SplitType.EQUAL,
                1500);

        groupService.addExpense(
                "G1",
                "E2",
                "Lunch",
                junaid,
                List.of(new Split(faiz), new Split(zaid), new Split(junaid)),
                SplitType.EQUAL,
                600);

        groupService.printBalances("G1");
    }
}
