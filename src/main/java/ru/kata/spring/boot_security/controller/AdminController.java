package ru.kata.spring.boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.models.User;
import ru.kata.spring.boot_security.service.UserDetailsServiceImpl;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private UserDetailsServiceImpl userService;

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.getUsers());
        return "/admin/admin";
    }

    @GetMapping("/admin/users")
    public String listAllUsers(Model model) {
        List<User> list = userService.getUsers();
        model.addAttribute("users", list);

        return "admin/users";
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