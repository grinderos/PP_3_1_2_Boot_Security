package ru.kata.spring.boot_security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.models.User;
import ru.kata.spring.boot_security.repositories.RoleRepository;
import ru.kata.spring.boot_security.service.UserDetailsServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    private final RoleRepository roleRepository;
    private UserDetailsServiceImpl userService;

    @Autowired
    public AdminController(UserDetailsServiceImpl userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
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
        return "admin/new";
    }

    @PostMapping("/admin/add")
    public String add(@ModelAttribute("user") User user, ModelMap model) {
        if (user.getRoles().isEmpty()) {
            user.addRole(roleRepository.findByName("ROLE_USER"));
        }
        if (!userService.save(user)) {
            ArrayList<String> messages = new ArrayList<>();
            messages.add("Пользователь с таким логином уже существует");
            model.addAttribute("messages", messages);
            System.out.println("Ошибка при добавлении. Пользователь существует");
            return "admin/new";
        }

        System.out.println("end of saveNewUser method");
        return "redirect:/admin/users";
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
        if (user.getRoles().isEmpty()) {
            user.addRole(roleRepository.findByName("ROLE_USER"));
        }
        if (user.getPassword().length()==60) {
            userService.update(user);
        }else {
            userService.updateWithPass(user);
        }
        return "redirect:/admin/users";
    }

    @PostMapping("/admin/delete")
    public String deleteUser(@RequestParam("id") Long id, Authentication authentication, HttpSession session) {
        User deletedUser = userService.findUserById(id);
        userService.deleteUserById(id);
        if(deletedUser.getUsername().equals(authentication.getName())){
            session.invalidate();
            return "redirect:/";
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/truncate")
    public String truncate() {
        userService.truncate();
        return "redirect:/";
    }
}