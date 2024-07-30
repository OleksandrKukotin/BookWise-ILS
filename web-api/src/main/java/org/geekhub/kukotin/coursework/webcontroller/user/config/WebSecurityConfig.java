package org.geekhub.kukotin.coursework.webcontroller.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;

public class WebSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(requests -> requests
                .requestMatchers("/", "/register").permitAll()
                .anyRequest().authenticated())
            .formLogin(requests -> requests
                .loginPage("/login")
                .permitAll())
            .logout(LogoutConfigurer::permitAll);
        return http.build();
    }
}
