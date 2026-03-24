package design_problems.personal.due.splitwise.repository;

import design_problems.personal.due.splitwise.model.Expense;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseRepository {
    private final Map<String, List<Expense>> store = new HashMap<>();


    public void save(String groupId, Expense expense) {
        store.putIfAbsent(groupId, new ArrayList<>());
        store.get(groupId).add(expense);
    }


    public List<Expense> findByGroupId(String groupId) {
        return store.getOrDefault(groupId, new ArrayList<>());
    }
}
