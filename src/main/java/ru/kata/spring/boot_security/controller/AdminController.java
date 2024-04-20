package ru.kata.spring.boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.service.UserDetailsServiceImpl;

@Controller
public class AdminController {
    @Autowired
    private UserDetailsServiceImpl userService;

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.getUsers());
        return "/admin/admin";
    }

//    @PostMapping("/admin")
//    public String deleteUser(@RequestParam(required = true, defaultValue = "" ) Long id,
//                              @RequestParam(required = true, defaultValue = "" ) String action,
//                              Model model) {
//        if (action.equals("delete")){
//            userService.deleteUserById(id);
//        }
//        return "redirect:/admin";
//    }
}