package ru.kata.spring.boot_security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
