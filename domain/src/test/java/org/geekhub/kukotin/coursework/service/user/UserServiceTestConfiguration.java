package org.geekhub.kukotin.coursework.service.user;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class UserServiceTestConfiguration {

    @Bean
    public UserService userService(UserRepository userRepository, AuthorityRepository authorityRepository) {
        return new UserService(userRepository, authorityRepository);
    }
}
