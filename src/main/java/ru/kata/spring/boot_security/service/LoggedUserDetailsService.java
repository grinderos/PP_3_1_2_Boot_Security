package ru.kata.spring.boot_security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.models.LoggedUser;
import ru.kata.spring.boot_security.repositories.UserRepository;
import ru.kata.spring.boot_security.security.LoggedUserDetails;

import java.util.Optional;

@Service
public class LoggedUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public LoggedUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<LoggedUser> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь '"+username + "' не найден");
        }

        return new LoggedUserDetails(user.get());
    }
}

