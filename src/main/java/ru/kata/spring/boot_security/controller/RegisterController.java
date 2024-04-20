//package ru.kata.spring.boot_security.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import ru.kata.spring.boot_security.models.User;
//import ru.kata.spring.boot_security.service.UserDetailsServiceImpl;
//
//import javax.validation.Valid;
//
//@Controller
//@RequestMapping("/auth")
//public class RegisterController {
//    @Autowired
//    private UserDetailsServiceImpl userService;
//
//    @GetMapping("/reg")
//    public String registration(@ModelAttribute("user") Model model) {
//        return "auth/register";
//    }
//
//    @PostMapping("/reg")
//    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
//
//        if (bindingResult.hasErrors()) {
//            return "auth/register";
//        }
////        if (!user.getPassword().equals(user.getPasswordConfirm())){
////            model.addAttribute("passwordError", "Пароли не совпадают");
////            return "registration";
////        }
//        if (!userService.save(user)){
//            model.addAttribute("usernameAlreadyExists", "Пользователь с таким именем уже существует");
//            return "auth/register";
//        }
//
//        return "redirect:/";
//    }
//}
