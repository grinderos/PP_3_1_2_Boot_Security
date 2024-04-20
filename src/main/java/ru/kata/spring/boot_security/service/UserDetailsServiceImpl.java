package ru.kata.spring.boot_security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.models.User;
import ru.kata.spring.boot_security.models.Role;
import ru.kata.spring.boot_security.repositories.RoleRepository;
import ru.kata.spring.boot_security.repositories.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, RoleRepository roleRepository
            , PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Пользователь '" + username + "' не найден");
        }
        return user;
    }

    public User findUserById(Long id) {
        Optional<User> userFromDb = userRepository.findById(id);
        return userFromDb.orElse(new User());
    }

    @Transactional
    public boolean save(User user) {
        User loadedUserFromDB = userRepository.findByUsername(user.getUsername());
        if (loadedUserFromDB != null) {
            return false;
        }

        user.setRoles(Collections.singleton(new Role(1, "ROLE_ADMIN")));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void deleteUserById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        }
    }

    /*
    Далее идут вспомогательные методы, чтоб не лазить каждый раз в Workbench
    */
//    @Transactional
//    public void truncateTable() {
//        userRepository.truncateTable();
//    }


//    @Transactional
//    public void fillUsersTable() {
//        List<User> user = new ArrayList<>();
//        User admin = new User("admin","admin",30);
//        User user = new User("user","user",20);
//        user.add(admin);
//        user.add(user);
//        userRepository.saveAll(user);
//    }
}

