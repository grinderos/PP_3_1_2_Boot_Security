package ru.kata.spring.boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.models.User;
import ru.kata.spring.boot_security.service.UserDetailsServiceImpl;

import java.util.List;

@Controller
public class AdminController {

    private UserDetailsServiceImpl userService;

    @Autowired
    public AdminController(UserDetailsServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin/admin_panel";
    }

    @GetMapping("/admin/users")
    public String listAllUsers(Model model) {
        List<User> list = userService.getUsers();
        model.addAttribute("users", list);

        return "admin/users";
    }

    @GetMapping("/admin/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listRoles", userService.getRoles());
        return "admin/edit";
    }

    @PostMapping("/admin/edit")
    public String editUser(@RequestParam("id") Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("listRoles", userService.getRoles());
        return "admin/edit";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        if (!userService.updateWithPass(user)) {
            System.out.println("Пользователь не был сохранен");
            return "admin/edit";
        }
        return "redirect:admin/users";
    }

    @PostMapping("/admin/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:users";
    }

    @GetMapping("/admin/truncate")
    public String truncate() {
        userService.truncate();
        return "redirect:/";
    }
}