package design_problems.personal.splitwise.model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;

public class BalanceSheet {

    public static class Node {
        public User user;
        public double amount;

        public Node(User user, double amount) {
            this.user = user;
            this.amount = amount;
        }
    }

    private final Map<User, Map<User, Double>> balances;

    public BalanceSheet() {
        this.balances = new ConcurrentHashMap<>();
    }

    public void addUser(User user) {
        balances.putIfAbsent(user, new HashMap<>());
    }

    public void update(User from, User to, double amount) {
        balances.putIfAbsent(from, new HashMap<>());
        balances.putIfAbsent(to, new HashMap<>());

        Map<User, Double> fromMap = balances.get(from);
        Map<User, Double> toMap = balances.get(to);

        fromMap.putIfAbsent(to, 0.0);
        toMap.putIfAbsent(from, 0.0);

        fromMap.put(to, fromMap.get(to) + amount);
        toMap.put(from, toMap.get(from) - amount);
    }

    // V.V.Imp
    public void printSimplifiedDebts() {
        Map<User, Double> netBalances = new HashMap<>();

        for (User from: balances.keySet()) {
            netBalances.putIfAbsent(from, 0.0);

            for (Double amount: balances.get(from).values()) {
                netBalances.put(from, netBalances.get(from) + amount);
            }
        }

        PriorityQueue<Node> debtors =
                new PriorityQueue<>(Comparator.comparingDouble(a -> a.amount));

        PriorityQueue<Node> creditors =
                new PriorityQueue<>((a, b) -> Double.compare(b.amount, a.amount));

        for (User user: netBalances.keySet()) {
            if(netBalances.get(user) > 0) {
                creditors.add(new Node(user, netBalances.get(user)));
            } else if(netBalances.get(user) < 0) {
                debtors.add(new Node(user, -netBalances.get(user)));
            }
        }

        if(debtors.isEmpty() && creditors.isEmpty()) {
            System.out.println("All balances are settled!");
            return;
        }

        System.out.println("\n--- Minimum Transactions ---");
        while(!debtors.isEmpty() && !creditors.isEmpty()) {
            Node creditor = creditors.poll();
            Node debtor = debtors.poll();

            assert debtor != null; // ignore in interview

            double settlingAmount =
                    Math.min(debtor.amount, creditor.amount);

            System.out.println(debtor.user.name + " owes " + settlingAmount + " to " + creditor.user.name);

            debtor.amount -= settlingAmount;
            creditor.amount -= settlingAmount;

            if(debtor.amount != 0.0) {
                debtors.add(debtor);
            }
            if(creditor.amount != 0.0) {
                creditors.add(creditor);
            }
        }
    }

    public Map<User, Map<User, Double>> getBalances() {
        return balances;
    }
}
