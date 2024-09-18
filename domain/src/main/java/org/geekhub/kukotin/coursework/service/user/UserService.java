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
        user.setRole("ROLE_USER");
        authorityRepository.addAuthority(user.getUsername(), user.getRole());
    }

    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            String role = authorityRepository.getAuthority(user.getUsername());
            user.setRole(role);
        });
        return users;
    }

    public void remove(String username) {
        authorityRepository.removeAuthority(username);
        userRepository.deleteByUsername(username);
    }

    public void toggleUserStatus(String username) {
        userRepository.toggleUser(username);
    }

    public void addRole(String username, String role) {
        if (authorityRepository.isUserAnonymous(username)) {
            changeRole(username, role);
        }
    }

    public void changeRole(String username, String newRole) {
        authorityRepository.changeAuthority(username, newRole);
    }
}
