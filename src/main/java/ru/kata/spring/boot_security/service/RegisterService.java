//package ru.kata.spring.boot_security.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import ru.kata.spring.boot_security.models.User;
//import ru.kata.spring.boot_security.models.Role;
//import ru.kata.spring.boot_security.repositories.UserRepository;
//
//import java.util.Collection;
//import java.util.HashSet;
//
//@Service
//public class RegisterService {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public RegisterService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Transactional
//    public void register(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        Collection<Role> roles = new HashSet<>();
//        roles.add(new Role(2,"ROLE_USER"));
//
//        user.setRoles(roles);
////        userRepository.save(user);
//    }
//}
