package ru.kata.spring.boot_security.config;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("start");
//        registry.addViewController("/login").setViewName("/auth/login");
        registry.addViewController("/admin").setViewName("/admin/admin_panel");
    }
}
