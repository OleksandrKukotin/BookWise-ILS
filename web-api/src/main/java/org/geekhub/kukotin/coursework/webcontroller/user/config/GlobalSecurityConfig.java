package org.geekhub.kukotin.coursework.webcontroller.user.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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
                .authorizeHttpRequests(apiRequests -> apiRequests
                        .requestMatchers("/api/**").hasRole("LIBRARIAN")
                        .requestMatchers("/api/users/**").hasRole("ADMIN"))
                .authorizeHttpRequests(webRequests -> webRequests
                        .requestMatchers("/", "/home/*", "/css/**").permitAll()
                        .requestMatchers("/register").anonymous())
                .formLogin(requests -> requests
                        .loginPage("/login").permitAll())
                .logout(logoutRequest -> logoutRequest
                        .logoutUrl("/logout").permitAll()
                        .logoutSuccessUrl("/home"));
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        Dotenv dotenv = Dotenv.configure().filename(".env/pass.env").load();
        UserDetails librarian = User.builder()
                .username("librarius")
                .password(passwordEncoder.encode(dotenv.get("SPRING_SECURITY_TEST_PASSWORD")))
                .roles("LIBRARIAN")
                .build();
        JdbcUserDetailsManager detailsManager = new JdbcUserDetailsManager(dataSource);
        if (!detailsManager.userExists(librarian.getUsername())) {
            detailsManager.createUser(librarian);
        }
        return detailsManager;
    }
}
