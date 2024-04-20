//package ru.kata.spring.boot_security.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import ru.kata.spring.boot_security.models.User;
//
//import javax.validation.Valid;
//import java.util.ArrayList;
//import java.util.List;
////import ru.kata.spring.boot_security.service.AdminService;
//
//@Controller
//public class HelloController {
//
////    private final AdminService adminService;
//
////    @Autowired
////    public HelloController(AdminService adminService) {
////        this.adminService = adminService;
////    }
//
//
//    @GetMapping("/")
//    public String login(){
//        return "hello";
//    }
//
//    @GetMapping("/login")
//    public String loginPage() {
//        return "auth/login";
//    }
//
//    @GetMapping("/register")
//    public String registerPage(@ModelAttribute("user") User user) {
//        return "auth/register";
//    }
//
//    @PostMapping("/register")
//    public String registerSubmit(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
//        loggedUserValidator.validate(user, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "auth/register";
//        }
//        registerService.register(user);
//        return "redirect:/auth/login";
//    }
////
////    @GetMapping("/showUserInfo")
////    public String showUserInfo(){
////        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
////        System.out.println(userDetails.getUsername());
////        return "hello";
////    }
//
////    @GetMapping("/admin")
////    public String adminPage(){
//////        adminService.admining();
////        return "admin";
////    }
//
//
//    @GetMapping("/")
//    public String sayHello(ModelMap modelMap) {
//        List<String> messages = new ArrayList<>();
//        messages.add("Welcome on my page!");
//        messages.add("Click the button to authenticate and authorize!");
//        modelMap.addAttribute("messages", messages);
//        return "hello";
//    }
//}
