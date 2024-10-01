package org.geekhub.kukotin.coursework.webcontroller.config;

import org.springframework.beans.factory.annotation.Value;
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

@Configuration
public class GlobalSecurityConfig {

    private static final String ROLE_LIBRARIAN = "LIBRARIAN";
    private static final int REMEMBER_ME_TOKEN_VALIDITY_SECONDS = 3600;
    @Value("${REMEMBER_ME_KEY}")
    private String rememberMeKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(moderationRequests -> moderationRequests
                .requestMatchers("/api/**").hasRole(ROLE_LIBRARIAN)
                .requestMatchers("/administrator/**").hasRole("ADMIN"))
            .authorizeHttpRequests(webRequests -> webRequests
                .requestMatchers("/", "/home/*", "/css/**").permitAll()
                .requestMatchers("/registration", "/registration/submit").permitAll()
                .requestMatchers("/passwordReset").permitAll())
            .formLogin(requests -> requests
                .loginPage("/login").permitAll())
            .logout(logoutRequest -> logoutRequest
                .logoutUrl("/logout").permitAll()
                .logoutSuccessUrl("/home"))
            .rememberMe(rememberMe -> rememberMe.key(rememberMeKey)
                .tokenValiditySeconds(REMEMBER_ME_TOKEN_VALIDITY_SECONDS));
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
