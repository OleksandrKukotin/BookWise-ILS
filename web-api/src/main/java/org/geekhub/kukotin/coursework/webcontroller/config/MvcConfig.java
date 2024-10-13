package org.geekhub.kukotin.coursework.webcontroller.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/home").setViewName("index");
        registry.addViewController("/error").setViewName("errorPage");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/register").setViewName("registration");
        registry.addViewController("/administrator/users").setViewName("userManagement");
        registry.addViewController("/forgot-password").setViewName("forgotPasswordForm");
        registry.addViewController("/forgot-password-confirmation").setViewName("forgotPasswordConfirmation");
        // add session expired logout page
    }
}
