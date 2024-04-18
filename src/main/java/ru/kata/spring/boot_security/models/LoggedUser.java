package ru.kata.spring.boot_security.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users_3_1_3")
public class LoggedUser {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 100, message = "Не должно быть менее 2 знаков")
    @Column(name = "username")
    private String username;

    @Column(name = "login_password")
    private String password;

    @Column(name = "age")
    private int age;


    public LoggedUser() {
    }

    public LoggedUser(String username, String password, int age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "LoggedUser:" +
                "\nid      = " + id +
                "\nusername= " + username +
                "\npassword= " + password+
                "\nage     = " + age;
    }
}
