package ru.kata.spring.boot_security.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "users_3_1_3")
public class User {
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

    @ManyToMany(fetch = FetchType.EAGER)
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
//    @Fetch(FetchMode.JOIN)
//    @JoinTable(name = "user_role_3_1_3",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    public User() {
    }

//    public User(String username, int age, String password) {
//        this.username = username;
//        this.age = age;
//        this.password = password;
//    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        System.out.println("get password: "+password);
        return password;
    }

    public void setPassword(String password) {
        System.out.println("set password: "+password);
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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
}
