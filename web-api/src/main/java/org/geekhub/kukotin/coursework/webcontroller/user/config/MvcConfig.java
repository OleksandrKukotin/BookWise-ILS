package org.geekhub.kukotin.coursework.webcontroller.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("index");
        registry.addViewController("/test").setViewName("test");
        registry.addViewController("/register").setViewName("registration");
        registry.addViewController("/login").setViewName("login");
    }

}
