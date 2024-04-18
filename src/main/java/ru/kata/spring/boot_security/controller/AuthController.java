package ru.kata.spring.boot_security.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.models.LoggedUser;
import ru.kata.spring.boot_security.service.RegisterService;
import ru.kata.spring.boot_security.util.LoggedUserValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final LoggedUserValidator loggedUserValidator;
    private final RegisterService registerService;


    @Autowired
    public AuthController(LoggedUserValidator loggedUserValidator, RegisterService registerService) {
        this.loggedUserValidator = loggedUserValidator;
        this.registerService = registerService;
    }


    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerPage(@ModelAttribute("user") LoggedUser user) {
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute("user") @Valid LoggedUser user, BindingResult bindingResult) {
        loggedUserValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "auth/register";
        }
        registerService.register(user);
        return "redirect:/auth/login";
    }
}
