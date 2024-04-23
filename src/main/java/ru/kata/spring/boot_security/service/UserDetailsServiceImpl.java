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

import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

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

    @Transactional(readOnly = true)
    public User findUserById(Long id) {
        Optional<User> userFromDb = userRepository.findById(id);
        return userFromDb.orElse(new User());
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public boolean save(User user) {
        User loadedUserFromDB = userRepository.findByUsername(user.getUsername());
        if (loadedUserFromDB != null) {
            return false;
        }
        System.out.println("сохранение пользователя \n" + user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        userRepository.save(user);
        return true;
    }

    @Transactional
    public boolean updateWithPass(User user) {
        System.out.println("сохранение пользователя \n" + user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        userRepository.save(user);
        return true;
    }

    @Transactional
    public boolean update(User user) {
        System.out.println("сохранение пользователя \n" + user);
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

    @Transactional
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    /*
    Далее идут вспомогательные методы, чтоб не лазить каждый раз в Workbench
    */
    @Transactional
    public void truncate() {
        userRepository.truncateUsers();
        userRepository.truncateUser_role();
    }

    @Transactional
    public void fillUsers() {
        if(roleRepository.findAll().isEmpty()){
//            roleRepository.fillRoles();
            fillRoles();
        }
        User admin = new User("admin", 33, passwordEncoder.encode("admin"));
        admin.setRoles(new HashSet<>(roleRepository.findAll()));
        User user = new User("user", 22, passwordEncoder.encode("user"));
        user.addRole(roleRepository.findByName("ROLE_USER"));
        User loadedUserFromDB = userRepository.findByUsername(admin.getUsername());
        if (loadedUserFromDB == null) {
            userRepository.save(admin);
        }
        loadedUserFromDB = null;
        loadedUserFromDB = userRepository.findByUsername(user.getUsername());
        System.out.println("\n");
        System.out.println(loadedUserFromDB);
        System.out.println("\n");
        if (loadedUserFromDB == null) {
            userRepository.save(user);
        }
    }

    @Transactional
    public void fillRoles(){
//        roleRepository.fillRoles();
        roleRepository.save(new Role("ROLE_ADMIN"));
        roleRepository.save(new Role("ROLE_USER"));
    }
}

