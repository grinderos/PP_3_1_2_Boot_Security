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
        return "/admin/admin";
    }

    @GetMapping("/admin/users")
    public String listAllUsers(Model model) {
        List<User> list = userService.getUsers();
        model.addAttribute("users", list);

        return "admin/users";
    }
    @PostMapping("/admin/new")
    public String newUser(@ModelAttribute User user, Model model) {
//        model.addAttribute("user", userService.findUserById(id));
        model.addAttribute("listRoles", userService.getRoles());
        return "/user/edit";
    }
    @PostMapping("/edit")
    public String editUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        model.addAttribute("listRoles", userService.getRoles());
        return "/admin/edit";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        System.out.println(user.getRoles());
//        userValidator.validate(user, bindingResult);
//        if (bindingResult.hasErrors()) {
//            System.out.println("bindingResult.hasErrors() есть ошибки");
//            return "/admin/edit";
//        }

        if (!userService.update(user)) {
            System.out.println("Пользователь не был сохранен");
//            model.addAttribute("usernameAlreadyExists", "Пользователь с таким именем уже существует");
            return "/admin/edit";
        }
        return "redirect:/admin";
    }

    @PostMapping("/admin/delete")
    public String deleteUser(@RequestParam("id") Long id) {
            userService.deleteUserById(id);
        return "redirect:/admin";
    }
}