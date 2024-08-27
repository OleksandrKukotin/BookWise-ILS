package org.geekhub.kukotin.coursework.service.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void add(User user) {
        repository.save(user);
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public void update(String username, User newData) {
        repository.update(username, newData);
    }

    public void remove(User user) {
        repository.deleteByUsername(user.username());
    }
}
