package ru.kata.spring.boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.security.LoggedUserDetails;
import ru.kata.spring.boot_security.service.AdminService;

@Controller
public class HelloController {

    private final AdminService adminService;

    @Autowired
    public HelloController(AdminService adminService) {
        this.adminService = adminService;
    }


    @GetMapping("/hello")
    public String sayHello(){
        return "hello";
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoggedUserDetails userDetails = (LoggedUserDetails)authentication.getPrincipal();
        System.out.println(userDetails.getUser());
        return "hello";
    }

    @GetMapping("/admin")
    public String adminPage(){
        adminService.admining();
        return "admin";
    }
}
