package ru.kata.spring.boot_security.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="roles")
public class Role implements GrantedAuthority {
    @Id
    @Column(name="id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="user_role", unique=true)
    private String role;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public Role(){}

    public Role(int id){
        this.id = id;
    }
    public Role(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return role;
    }

    @Override
    public String getAuthority() {
        return getRole();
    }
}
