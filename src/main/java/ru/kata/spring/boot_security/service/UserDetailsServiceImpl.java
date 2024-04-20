package ru.kata.spring.boot_security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
import ru.kata.spring.boot_security.security.UserDetailsImpl;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDetailsImpl> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь '" + username + "' не найден");
        }
        return new org.springframework.security.core.userdetails.User(
                user.get().getUsername(), user.get().getPassword(), user.get().getAuthorities());
    }


    public User findById(Long id) {
        Optional<UserDetailsImpl> user = userRepository.findById(id);
        return user.orElse(new UserDetailsImpl()).getUser();
    }

    @Transactional
    public boolean save(UserDetailsImpl user) {
        if (!(userRepository.findByUsername(user.getUsername()).isEmpty())) {
            return false;
        }
        user.getUser().setRoles(Collections.singleton(new Role(2,"ROLE_USER")));
        user.getUser().setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }


    @Transactional(readOnly = true)
    public List<UserDetailsImpl> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void deleteUserById(long id){
        userRepository.deleteById(id);
    }

    /*
    Далее идут вспомогательные методы, чтоб не лазить каждый раз в Workbench
    */
//    @Transactional
//    public void truncateTable() {
//        userRepository.truncateTable();
//    }
    @Transactional
    public void fillUsersTable(){
        List<UserDetailsImpl> users = new ArrayList<UserDetailsImpl>();
        UserDetailsImpl admin = new UserDetailsImpl(new User());
        admin.getUser().setUsername("admin");
        admin.getUser().setPassword("admin");
        admin.getUser().setAge(30);
        UserDetailsImpl user = new UserDetailsImpl(new User());
        admin.getUser().setUsername("user");
        admin.getUser().setPassword("user");
        admin.getUser().setAge(20);
        users.add(admin);
        users.add(user);
        userRepository.saveAll(users);
    }
}

