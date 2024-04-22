package ru.kata.spring.boot_security.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.models.Role;
import ru.kata.spring.boot_security.models.User;
import ru.kata.spring.boot_security.service.UserDetailsServiceImpl;
import ru.kata.spring.boot_security.util.UserValidator;

import java.util.ArrayList;

@Controller
public class AuthController {

    private final UserValidator userValidator;
    private UserDetailsServiceImpl userService;

    @Autowired
    public AuthController(UserValidator userValidator, UserDetailsServiceImpl userService) {
        this.userValidator = userValidator;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/")
    public String start(ModelMap model) {
        ArrayList<String> messages = new ArrayList<>();
        messages.add("Для входа с имеющимся именем пользователя выберите 'войти'");
        messages.add("Для регистрации выберите 'регистрация'");
        model.addAttribute("messages", messages);
        return "/start";
    }

    @GetMapping("/auth/register")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listRoles", userService.getRoles());
        return "/auth/register";
    }

    @PostMapping("/auth/register_new_user")
    public String addUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
        if(user.getRoles().isEmpty()) {
            user.addRole(new Role("ROLE_USER"));
        }
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            System.out.println("bindingResult.hasErrors() есть ошибки");
            return "/auth/register";
        }

        if (!userService.save(user)) {
            System.out.println("Пользователь не был сохранен");
            return "/auth/register";
        }
        return "redirect:/";
    }
}
