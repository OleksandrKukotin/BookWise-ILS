package org.geekhub.kukotin.coursework.webcontroller.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class GlobalSecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final DataSource dataSource;

    public GlobalSecurityConfig(PasswordEncoder passwordEncoder, DataSource dataSource) {
        this.passwordEncoder = passwordEncoder;
        this.dataSource = dataSource;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(requests -> requests
                .requestMatchers("/home", "/css/**").permitAll()
                .requestMatchers("/register").anonymous()
                .requestMatchers("/test").authenticated()
                .requestMatchers("/api/**").hasRole("LIBRARIAN")
                .requestMatchers("/api/users/**").hasRole("ADMIN"))
            .formLogin(requests -> requests
                .loginPage("/login"))
            .logout(LogoutConfigurer::permitAll);
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails librarian = User.builder()
            .username("librarius")
            .password(passwordEncoder.encode("replaceWithEnvUsing"))
            .roles("LIBRARIAN")
            .build();
        JdbcUserDetailsManager detailsManager = new JdbcUserDetailsManager(dataSource);
        if (!detailsManager.userExists(librarian.getUsername())) {
            detailsManager.createUser(librarian);
        }
        return detailsManager;
    }
}
