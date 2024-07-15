package org.geekhub.kukotin.coursework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {

    private static final int PASSWORD_STRENGTH = 7;

    @Bean
    PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(PASSWORD_STRENGTH);
    }
}
