package org.geekhub.kukotin.coursework.service.user;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthorityRepository authorityRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testAddUser() {
        User testUser = new User("John Doe", "example@gmail.com", "ROLE_USER", "1111",
            true);

        userService.add(testUser);
    }

    @Test
    void getUsersPage() {
    }

    @Test
    void getTotalUsersCount() {
    }

    @Test
    void getUserByEmail() {
    }

    @Test
    void remove() {
    }

    @Test
    void toggleUserStatus() {
    }

    @Test
    void changeRole() {
    }

    @Test
    void changePassword() {
    }

    @Test
    void getUserByUsername() {
    }
}
