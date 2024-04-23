package ru.kata.spring.boot_security.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @NotEmpty(message = "Поле не должно быть пустым")
//    @Size(min = 3, max = 40, message = "Логин должен содержать не менее трех символов")
    @Column(name = "username", nullable = false, unique = true, length = 32)
    private String username;

    //    @NotEmpty(message = "Поле не должно быть пустым")
    @Column(name = "login_password", nullable = false, length = 64)
//    @Size(min = 4, max = 100, message = "Пароль должен содержать не менее трех символов")
    private String password;

    @Column(name = "age")
//    @Min(value = 0, message = "Неверно указан возраст")
    private Integer age;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String username, int age, String password) {
        this.username = username;
        this.age = age;
        this.password = password;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        System.out.println("set password: " + password);
        this.password = password;
    }

    @Override
    public String getPassword() {
        System.out.println("get password: " + password);
        return password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    @Override
    public String toString() {
        return "User:" +
                "\nid      = " + id +
                "\nusername= " + username +
                "\npassword= " + password +
                "\nage     = " + age +
                "\nroles   = " + roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
