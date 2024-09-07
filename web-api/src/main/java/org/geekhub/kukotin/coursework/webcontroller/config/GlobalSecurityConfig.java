package org.geekhub.kukotin.coursework.webcontroller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class GlobalSecurityConfig {

    private final DataSource dataSource;

    public GlobalSecurityConfig(DataSource dataSource) {
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
                        .requestMatchers("/registration", "/registration/submit").permitAll())
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
        return new JdbcUserDetailsManager(dataSource);
    }
}
