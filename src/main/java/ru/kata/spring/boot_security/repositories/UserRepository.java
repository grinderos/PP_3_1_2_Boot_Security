package ru.kata.spring.boot_security.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = "truncate table users;", nativeQuery = true)
    void truncateUsers();

    @Modifying
    @Transactional
    @Query(value = "truncate table user_role;", nativeQuery = true)
    void truncateUser_role();
}
