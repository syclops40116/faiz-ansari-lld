package design_problems.personal.due.splitwise.model;

import java.util.*;

public class BalanceSheet {

    private final Map<String, Map<String, Double>> balances = new HashMap<>();


    public void addUser(User user) {
        balances.putIfAbsent(user.getId(), new HashMap<>());
    }


    public void update(User from, User to, double amount) {


        balances.putIfAbsent(from.getId(), new HashMap<>());
        balances.putIfAbsent(to.getId(), new HashMap<>());


        balances.get(from.getId()).put(to.getId(), balances.get(from.getId()).getOrDefault(to.getId(), 0.0) + amount);


        balances.get(to.getId())
                .put(from.getId(), balances.get(to.getId()).getOrDefault(from.getId(), 0.0) - amount);
    }


    public Map<String, Map<String, Double>> getBalances() {
        return balances;
    }


    // Simplify using Greedy
    public List<String> simplify() {


        Map<String, Double> net = new HashMap<>();


        for (String u : balances.keySet()) {
            for (double v : balances.get(u).values()) {
                net.put(u, net.getOrDefault(u, 0.0) + v);
            }
        }


        PriorityQueue<Node> debtors = new PriorityQueue<>(Comparator.comparingDouble(a -> a.amount));
        PriorityQueue<Node> creditors = new PriorityQueue<>((a, b) -> Double.compare(b.amount, a.amount));


        for (String u : net.keySet()) {
            double val = net.get(u);


            if (val < 0) debtors.add(new Node(u, -val));
            else if (val > 0) creditors.add(new Node(u, val));
        }


        List<String> result = new ArrayList<>();


        while (!debtors.isEmpty() && !creditors.isEmpty()) {


            Node d = debtors.poll();
            Node c = creditors.poll();


            double settle = Math.min(d.amount, c.amount);


            result.add(d.id + " pays " + c.id + " Rs." + settle);


            d.amount -= settle;
            c.amount -= settle;


            if (d.amount > 0.01) debtors.add(d);
            if (c.amount > 0.01) creditors.add(c);
        }


        return result;
    }
}
