package ru.kata.spring.boot_security.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.models.LoggedUser;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<LoggedUser, Integer> {
    Optional<LoggedUser> findByUsername(String username);
}
