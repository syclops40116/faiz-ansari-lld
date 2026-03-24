package design_problems.personal.due.splitwise.repository;

import design_problems.personal.due.splitwise.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {


    private final Map<String, User> store = new HashMap<>();


    public void save(User user) {
        store.put(user.getId(), user);
    }


    public User findById(String id) {
        return store.get(id);
    }
}

