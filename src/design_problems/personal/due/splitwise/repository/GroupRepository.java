package design_problems.personal.due.splitwise.repository;

import design_problems.personal.due.splitwise.model.Group;

import java.util.HashMap;
import java.util.Map;

public class GroupRepository {


    private final Map<String, Group> store = new HashMap<>();


    public void save(Group group) {
        store.put(group.getId(), group);
    }


    public Group findById(String id) {
        return store.get(id);
    }
}
