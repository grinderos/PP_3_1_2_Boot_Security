package ru.kata.spring.boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.models.Role;
import ru.kata.spring.boot_security.models.User;
import ru.kata.spring.boot_security.service.UserDetailsServiceImpl;

import java.security.Principal;


@Controller
//@RequestMapping
public class UserController {

    private UserDetailsServiceImpl userService;
    private final Role checkAdmin = new Role("ROLE_ADMIN");

    @Autowired
    public UserController(UserDetailsServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String getUserInfo(Model model, Principal principal) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = (User)authentication.getPrincipal();
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", userService.loadUserByUsername(user.getUsername()));
        return "/user";
    }

     @GetMapping("/to_main")
    public String toMain(Principal principal) {
         User user = userService.findByUsername(principal.getName());
         if(user.getRoles().contains(checkAdmin)){
             return "redirect:/admin";
         } else {
             return "redirect:/user";
         }
     }


//    @PostMapping("/user/edit")
//    public String editUser(@RequestParam("id") String username, Model model) {
//        model.addAttribute("user", userService.findByUsername(username));
//        return "user/editOrNew";
//    }

//    @PostMapping("/user/addOrUpdate")
//    public String addUserToBD(@ModelAttribute("user") User user) {
//        if(user.getAge()<0 || user.getUsername().equals("")){
//            return "/user/badFieldErr";
//        } else {
//            userService.save(user);
//            return "redirect:/user";
//        }
//    }

//    @PostMapping("/delete")
//    public String deleteUserFromBD(@RequestParam("id") Long id) {
//        userService.deleteUserById(id);
//        return "redirect:/user";
//    }

//    @GetMapping("/truncate")
//    public String truncateUsersTable() {
//        userService.truncateTable();
//        return "user/truncateOrFillTable";
//    }
//
//    @GetMapping("/fill")
//    public String fillUsersTable() {
//        userService.fillUsersTable();
//        return "user/truncateOrFillTable";
//    }
}