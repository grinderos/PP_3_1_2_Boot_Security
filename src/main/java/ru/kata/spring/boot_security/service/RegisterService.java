package ru.kata.spring.boot_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.models.LoggedUser;
import ru.kata.spring.boot_security.repositories.UserRepository;

@Service
public class RegisterService {
    private final UserRepository userRepository;

    @Autowired
    public RegisterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void register(LoggedUser user) {
        userRepository.save(user);
    }
}
