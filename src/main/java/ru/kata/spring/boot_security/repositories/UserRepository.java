package ru.kata.spring.boot_security.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.models.User;
import ru.kata.spring.boot_security.security.UserDetailsImpl;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDetailsImpl, Long> {
    Optional<UserDetailsImpl> findByUsername(String username);
}
