package org.geekhub.kukotin.coursework.service.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);
    void deleteByUsername(String username);
    List<User> findAll();
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    void toggleUser(String username);
    void updatePassword(String username, String newPassword);
}
