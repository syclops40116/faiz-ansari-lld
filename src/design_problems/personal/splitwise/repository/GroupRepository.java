package design_problems.personal.splitwise.repository;

import design_problems.personal.splitwise.model.Group;

import java.util.HashMap;
import java.util.Map;

public class GroupRepository {
    Map<String, Group> groups = new HashMap<>();

    public void save(Group group) {
        groups.put(group.id, group);
    }

    public Group getById(String id) {
        return groups.get(id);
    }
}
