package org.geekhub.kukotin.coursework.service.user;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    public UserService(
        UserDetailsManager userDetailsManager,
        PasswordEncoder passwordEncoder
    ) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(UserDetails user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        UserDetails userWithEncodedPassword = User.withUserDetails(user)
            .password(encodedPassword)
            .build();

        userDetailsManager.createUser(userWithEncodedPassword);
    }

    public void deleteUser(String username) {
        userDetailsManager.deleteUser(username);
    }

    public void changePassword(String oldPassword, String newPassword) {
        var encodedNewPassword = passwordEncoder.encode(newPassword);
        userDetailsManager.changePassword(oldPassword, encodedNewPassword);
    }

    public boolean userExists(String username) {
        return userDetailsManager.userExists(username);
    }

    public UserDetails loadUserByUsername(String username) {
        return userDetailsManager.loadUserByUsername(username);
    }

}
