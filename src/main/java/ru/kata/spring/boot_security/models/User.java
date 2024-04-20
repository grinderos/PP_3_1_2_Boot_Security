package ru.kata.spring.boot_security.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 3, max = 40, message = "Логин должен содержать не менее трех символов")
    @Column(name = "username")
    private String username;

    @Column(name = "login_password")
    @Size(min = 3, max = 40, message = "Логин должен содержать не менее трех символов")
    private String password;

    @Column(name = "age")
    @Min(value = 0, message = "Неверно указан возраст")
    private int age;

//    @Column(name = "role")
//    private String role;

//    @ManyToMany(fetch = FetchType.EAGER)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    public User() {
    }

    public User(String username, String password, int age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        System.out.println("set password: "+password);
        this.password = password;
    }

    @Override
    public String getPassword() {
        System.out.println("get password: "+password);
        return password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Collection<Role> getRoles() {
        return roles;
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
