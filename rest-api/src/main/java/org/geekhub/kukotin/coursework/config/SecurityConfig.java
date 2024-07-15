package org.geekhub.kukotin.coursework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    private final PasswordEncoder encoder;

    public SecurityConfig(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, DataSource dataSource) throws Exception {
        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);

        auth
            .jdbcAuthentication()
            .passwordEncoder(encoder)
            .dataSource(dataSource)
            .authoritiesByUsernameQuery("select authority from authorities when username = ?");

        return auth.build();
    }

    @Bean
    public UserDetailsManager users(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authRegistry -> authRegistry
                .requestMatchers("/login").permitAll()
                .requestMatchers("/api/**").permitAll()
                .requestMatchers("/registration").anonymous()
                .anyRequest().permitAll()
            );

        return http.build();
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();

        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_LIBRARIAN > ROLE_USER > ROLE_GUEST");

        return roleHierarchy;
    }

}
