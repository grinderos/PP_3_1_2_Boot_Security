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

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class AuthController {

//    private final LoggedUserValidator loggedUserValidator;
//    private final RegisterService registerService;
//
//
//    @Autowired
//    public AuthController(LoggedUserValidator loggedUserValidator, RegisterService registerService) {
//        this.loggedUserValidator = loggedUserValidator;
//        this.registerService = registerService;
//    }


    @Autowired
    private UserDetailsServiceImpl userService;

    @GetMapping("/")
    public String hello(ModelMap model){
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
    public String registration(@ModelAttribute("user") User user) {
        return "/auth/register";
    }

    @PostMapping("/auth/register")
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/auth/register";
        }
//        if (!user.getPassword().equals(user.getPasswordConfirm())){
//            model.addAttribute("passwordError", "Пароли не совпадают");
//            return "registration";
//        }
        if (!userService.save(user)){
//            model.addAttribute("usernameAlreadyExists", "Пользователь с таким именем уже существует");
            return "auth/register";
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
