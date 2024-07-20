package org.geekhub.kukotin.coursework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    private final PasswordEncoder encoder;
    private final DataSource dataSource;

    public SecurityConfig(PasswordEncoder encoder, DataSource dataSource) {
        this.encoder = encoder;
        this.dataSource = dataSource;
    }

    @Bean
    public UserDetailsManager users() {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        var authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
            .jdbcAuthentication()
            .withUser("admin")
            .password("{bcrypt}$2a$10$rkWfnHrSpo0JyNBH4tHRDOeuZACtCU5v4sCQpleWl4P41YuYqQMjC") //admin
            .roles("ADMIN");

        authenticationManagerBuilder
            .jdbcAuthentication()
            .passwordEncoder(encoder)
            .dataSource(dataSource)
            .withUser("user")
            .password("{bcrypt}$2a$10$GlpFG1Ml3U9AvkOu0D1B9ufnoquX5xqCR/NHaMfBZliYgPa8/e5sK")
            .roles("USER");

        return authenticationManagerBuilder.build();
    }
}
