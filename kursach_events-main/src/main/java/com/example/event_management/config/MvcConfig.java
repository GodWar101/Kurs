package com.example.event_management.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("admin_dashboard");
        registry.addViewController("/index").setViewName("admin_dashboard");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/admin_dashboard").setViewName("admin_dashboard");
        registry.addViewController("/new_event").setViewName("new_event");
        registry.addViewController("/edit_event").setViewName("edit_event");
        registry.addViewController("/admin/venues").setViewName("index_ven");
        registry.addViewController("/admin_ven").setViewName("admin_ven");
        registry.addViewController("/author").setViewName("author");
        registry.addViewController("/description").setViewName("description");
        registry.addViewController("/edit_venue").setViewName("edit_venue");
        registry.addViewController("/new_venue").setViewName("new_venue");
        registry.addViewController("/users").setViewName("users");
    }
}
