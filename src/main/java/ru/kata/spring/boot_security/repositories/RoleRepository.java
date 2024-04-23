package ru.kata.spring.boot_security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO security_user.roles (user_role) values ('ROLE_USER'), ('ROLE_ADMIN');", nativeQuery = true)
    void fillRoles();
}
