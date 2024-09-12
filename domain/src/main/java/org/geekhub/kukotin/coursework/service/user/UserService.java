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

    public void remove(String username) {
        repository.deleteByUsername(username);
    }

    public void toggleUserStatus(String username) {
        repository.toggleUser(username);
    }
}
