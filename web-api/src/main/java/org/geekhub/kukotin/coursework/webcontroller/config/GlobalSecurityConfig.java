package org.geekhub.kukotin.coursework.webcontroller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;

@Configuration
public class GlobalSecurityConfig {

    public static final String ROLE_LIBRARIAN = "LIBRARIAN";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, RememberMeServices rememberMeServices)
        throws Exception {
        http
            .authorizeHttpRequests(moderationRequests -> moderationRequests
                .requestMatchers("/api/**").hasRole(ROLE_LIBRARIAN)
                .requestMatchers("/administrator/**").hasRole("ADMIN"))
            .authorizeHttpRequests(webRequests -> webRequests
                .requestMatchers("/", "/home/*", "/css/**").permitAll()
                .requestMatchers("/registration", "/registration/submit").permitAll())
            .formLogin(requests -> requests
                .loginPage("/login").permitAll())
            .logout(logoutRequest -> logoutRequest
                .logoutUrl("/logout").permitAll()
                .logoutSuccessUrl("/home"))
            .rememberMe(remember -> remember.rememberMeServices(rememberMeServices));
        http.sessionManagement(httpSecuritySessionManagementConfigurer ->
            httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    static RoleHierarchy roleHierarchy() {
        return RoleHierarchyImpl.withDefaultRolePrefix()
            .role("ADMIN").implies(ROLE_LIBRARIAN)
            .role(ROLE_LIBRARIAN).implies("USER")
            .role("USER").implies("GUEST")
            .build();
    }

    // For pre-post method security
    @Bean
    static MethodSecurityExpressionHandler methodSecurityExpressionHandler(RoleHierarchy roleHierarchy) {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy);
        return expressionHandler;
    }
}
