package org.geekhub.kukotin.coursework.service.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public UserService(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    public void add(User user) {
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void remove(String username) {
        userRepository.deleteByUsername(username);
    }

    public void toggleUserStatus(String username) {
        userRepository.toggleUser(username);
    }

    public void addRole(String username, String role) {
        // Check if role already exists for the user
        if (!authorityRepository.isUserHaveAuthority(username, role)) {
            authorityRepository.addAuthority(username, role);
        }
    }

    public void removeRole(String username, String role) {
        authorityRepository.removeAuthority(username, role);
    }

    public void changeRole(String username, String role) {
        authorityRepository.changeAuthority(username, role);
    }
}
