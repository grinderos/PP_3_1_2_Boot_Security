//package ru.kata.spring.boot_security.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import ru.kata.spring.boot_security.models.User;
//import ru.kata.spring.boot_security.service.UserService;
//
//
//@Controller
//@RequestMapping("/")
//public class UserController {
//
//    private UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping("/")
//    public String mainPage() {
//        return "mainPage";
//    }
//
//    @GetMapping("/users")
//    public String getUsersFromBD(Model models) {
//        models.addAttribute("users", userService.getUsers());
//        return "users/users";
//    }
//
//    @GetMapping("/users/new")
//    public String newUser(@ModelAttribute("user") User user) {
//        return "users/editOrNew";
//    }
//
//    @PostMapping("/users/edit")
//    public String editUser(@RequestParam("id") long id, Model models) {
//        models.addAttribute("user", userService.getUserById(id));
//        return "users/editOrNew";
//    }
//
//    @PostMapping("/users/addOrUpdate")
//    public String addUserToBD(@ModelAttribute("user") User user) {
//        if(user.getAge()<0 || user.getFirstName().equals("") || user.getLastName().equals("") || user.getEmail().equals("")){
//            return "/users/badFieldErr";
//        } else {
//            userService.add(user);
//            return "redirect:/users";
//        }
//    }
//
//    @PostMapping("/delete")
//    public String deleteUserFromBD(@RequestParam("id") long id) {
//        userService.deleteUser(id);
//        return "redirect:/users";
//    }
//
//    @GetMapping("/truncate")
//    public String truncateUsersTable() {
//        userService.truncateTable();
//        return "users/truncateOrFillTable";
//    }
//
//    @GetMapping("/fill")
//    public String fillUsersTable() {
//        userService.fillUsersTable();
//        return "users/truncateOrFillTable";
//    }
//}