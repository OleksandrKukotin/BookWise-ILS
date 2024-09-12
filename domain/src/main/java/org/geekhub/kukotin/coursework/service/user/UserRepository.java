package org.geekhub.kukotin.coursework.service.user;

import java.util.List;

public interface UserRepository {

    void save(User user);
    void deleteByUsername(String username);
    List<User> findAll();
    User findByUsername(String username);
    void toggleUser(String username);
}
