package org.geekhub.kukotin.coursework.service.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);
    void deleteByUsername(String username);
    List<User> findAll();
    User findByUsername(String username);
    void toggleUser(String username);
    Optional<User> findByEmail(String email);
}
