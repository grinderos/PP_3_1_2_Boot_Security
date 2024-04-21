package ru.kata.spring.boot_security.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.models.User;
import ru.kata.spring.boot_security.service.UserDetailsServiceImpl;
import ru.kata.spring.boot_security.util.UserValidator;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class AuthController {

    private final UserValidator userValidator;
//    private final RegisterService registerService;

    private UserDetailsServiceImpl userService;

    //
    @Autowired
    public AuthController(UserValidator userValidator, UserDetailsServiceImpl userService) {
        this.userValidator = userValidator;
        this.userService = userService;
    }


    @GetMapping("/")
    public String hello(ModelMap model) {
        ArrayList<String> messages = new ArrayList<>();
        messages.add("Для входа с имеющимся именем пользователя выберите 'LOGIN'");
        messages.add("Для регистрации выберите 'register'");
        model.addAttribute("messages", messages);
        return "/hello";
    }

    @GetMapping("/auth/login")
    public String loginPage() {
        return "/auth/login";
    }

    @GetMapping("/auth/register")
    public String registration(
//            @ModelAttribute("user") User user
            Model model
    ) {
        model.addAttribute("user", new User());
        model.addAttribute("listRoles", userService.getRoles());
        return "/auth/register";
    }

    @PostMapping("/auth/register")
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        System.out.println(user.getRoles());
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            System.out.println("bindingResult.hasErrors() есть ошибки");
            return "/auth/register";
        }
//        if (!user.getPassword().equals(user.getPasswordConfirm())){
//            model.addAttribute("passwordError", "Пароли не совпадают");
//            return "registration";
//        }
        if (!userService.save(user)) {
            System.out.println("Пользователь не был сохранен");
//            model.addAttribute("usernameAlreadyExists", "Пользователь с таким именем уже существует");
            return "/auth/register";
        }
        if (user.getRoles().contains("ROLE_ADMIN")) {
            System.out.println("зарегался админ");
            return "redirect:/admin/admin";
        } else if (user.getRoles().contains("ROLE_USER")) {
            System.out.println("зарегался юзер");
            return "redirect:/user/user";
        }
        return "redirect:/";
    }

//    @PostMapping("/reg")
//    public String regIt(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
//        loggedUserValidator.validate(user, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "auth/register";
//        }
//        registerService.register(user);
//        return "redirect:/auth/login";
//    }
}
