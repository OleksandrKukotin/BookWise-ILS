package org.geekhub.kukotin.coursework.service.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserDetailsManager userDetailsManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userDetailsManager, passwordEncoder);
    }

    @Test
    void createUser_ValidUser_Success() {
        UserDetails user = User.builder()
            .username("testUser")
            .password("password")
            .roles("USER")
            .build();

        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        userService.createUser(user);

        verify(passwordEncoder).encode("password");
        verify(userDetailsManager).createUser(argThat(userDetails -> userDetails.getUsername().equals("testUser") &&
            userDetails.getPassword().equals("encodedPassword")));
    }

    @Test
    void deleteUser_ExistingUser_Success() {
        userService.deleteUser("testUser");
        verify(userDetailsManager).deleteUser("testUser");
    }

    @Test
    void changePassword_ValidPasswords_Success() {

        userService.changePassword("oldPassword", "newPassword");

        verify(passwordEncoder).encode("newPassword");
        verify(userDetailsManager).changePassword("oldPassword",
            passwordEncoder.encode("newPassword"));
    }

    @Test
    void userExists_ExistingUser_ReturnsTrue() {
        when(userDetailsManager.userExists("testUser")).thenReturn(true);

        assertTrue(userService.userExists("testUser"));
    }

    @Test
    void userExists_NonExistingUser_ReturnsFalse() {
        when(userDetailsManager.userExists("nonExistingUser")).thenReturn(false);

        assertFalse(userService.userExists("nonExistingUser"));
    }

    @Test
    void loadUserByUsername_ExistingUser_ReturnsUserDetails() {
        UserDetails userDetails = User.builder()
            .username("testUser")
            .password("password")
            .roles("USER")
            .build();

        when(userDetailsManager.loadUserByUsername("testUser")).thenReturn(userDetails);

        assertEquals(userDetails, userService.loadUserByUsername("testUser"));
    }
}
