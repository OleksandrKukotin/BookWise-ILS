package org.geekhub.kukotin.coursework.service.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceTest {

    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;
    private UserService userService;

    @Test
    void testAddUser() {
        User testUser = new User("John Doe", "example@gmail.com", "ROLE_USER", "1111",
            true);

        userService.add(testUser);
        userRepository.save(testUser);
        Assertions.assertTrue(userService.getUserByUsername("John Doe").isPresent());
    }

    @Test
    void getUsersPage() {
        User testUser = new User("John Doe", "example@gmail.com", "ROLE_USER", "1111",
            true);
        User testUser2 = new User("John Doe II", "example2@gmail.com", "ROLE_USER", "1111",
            true);

        userService.add(testUser);
        userService.add(testUser2);

        List<User> users = userService.getUsersPage(1,5);

        Assertions.assertEquals(2, users.size());
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
