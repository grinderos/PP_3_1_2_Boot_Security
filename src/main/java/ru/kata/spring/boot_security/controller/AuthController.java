package ru.kata.spring.boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.models.User;
import ru.kata.spring.boot_security.repositories.RoleRepository;
import ru.kata.spring.boot_security.service.UserDetailsServiceImpl;

import java.util.ArrayList;

@Controller
public class AuthController {

    private UserDetailsServiceImpl userService;
    private RoleRepository roleRepository;

    @Autowired
    public AuthController(UserDetailsServiceImpl userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/login")
    public String login() {
        if (userService.getUsers().isEmpty()) {
            return "redirect:auth/register";
        }
        return "auth/login";
    }

    @GetMapping("/")
    public String start(ModelMap messageModel) {
        ArrayList<String> messages = new ArrayList<>();
        messages.add("Для входа с имеющимся именем пользователя выберите 'войти'");
        messages.add("Для регистрации выберите 'регистрация'");
        messageModel.addAttribute("messages", messages);
        return "/start";
    }

    @GetMapping("/auth/register")
    public String registration(Model model, ModelMap messageModel) {
        if(userService.getRoles().isEmpty()){
            userService.fillRoles();
        }
        if (userService.getUsers().isEmpty()) {
            ArrayList<String> messages = new ArrayList<>();
            messages.add("В системе нет ни одного пользователя.");
            messages.add("Зарегистрируйтесь, чтобы стать первым.");
            messageModel.addAttribute("messages", messages);
        }
        model.addAttribute("user", new User());
        model.addAttribute("listRoles", userService.getRoles());
        return "/auth/register";
    }

    @PostMapping("/auth/register_new_user")
    public String addUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
        if (user.getRoles().isEmpty()) {
            user.addRole(roleRepository.findByName("ROLE_USER"));
        }
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
