package ru.kata.spring.boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.models.User;
import ru.kata.spring.boot_security.service.UserDetailsServiceImpl;
import ru.kata.spring.boot_security.util.UserValidator;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminController {

    private UserDetailsServiceImpl userService;
    private final UserValidator userValidator;

    @Autowired
    public AdminController(UserValidator userValidator, UserDetailsServiceImpl userService) {
        this.userValidator = userValidator;
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
    public String updateUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("bindingResult.hasErrors() есть ошибки");
            return "admin/edit";
        }
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
}