package design_problems.personal.due.splitwise.service;

import design_problems.personal.due.splitwise.model.User;
import design_problems.personal.due.splitwise.repository.UserRepository;

public class UserService {


    private final UserRepository repository;


    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    public User createUser(String id, String name) {
        User u = new User(id, name);
        repository.save(u);
        return u;
    }


    public User getUser(String id) {
        return repository.findById(id);
    }
}