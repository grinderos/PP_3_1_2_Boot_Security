package ru.kata.spring.boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.models.User;
import ru.kata.spring.boot_security.security.UserDetailsImpl;
import ru.kata.spring.boot_security.service.UserDetailsServiceImpl;


@Controller
@RequestMapping("/")
public class UserController {

    private UserDetailsServiceImpl userService;

    @Autowired
    public UserController(UserDetailsServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String mainPage() {
        return "mainPage";
    }

    @GetMapping("/users")
    public String getUsersFromBD(Model models) {
        models.addAttribute("users", userService.getUsers());
        return "users/users";
    }

    @GetMapping("/users/new")
    public String newUser(@ModelAttribute("user") UserDetailsImpl user) {
        return "users/editOrNew";
    }

    @PostMapping("/users/edit")
    public String editUser(@RequestParam("id") String username, Model model) {
        model.addAttribute("user", userService.findByUsername(username));
        return "users/editOrNew";
    }

    @PostMapping("/users/addOrUpdate")
    public String addUserToBD(@ModelAttribute("user") UserDetailsImpl user) {
        if(user.getAge()<0 || user.getUsername().equals("")){
            return "/users/badFieldErr";
        } else {
            userService.save(user);
            return "redirect:/users";
        }
    }

    @PostMapping("/delete")
    public String deleteUserFromBD(@RequestParam("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }

//    @GetMapping("/truncate")
//    public String truncateUsersTable() {
//        userService.truncateTable();
//        return "users/truncateOrFillTable";
//    }

    @GetMapping("/fill")
    public String fillUsersTable() {
        userService.fillUsersTable();
        return "users/truncateOrFillTable";
    }
}