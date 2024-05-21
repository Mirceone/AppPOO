package com.example.javafxapp.model;

import com.example.javafxapp.Tasks;
import com.example.javafxapp.UserProfile;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users", schema = "todo_app")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "idUser")
    private Set<Tasks> tasks = new LinkedHashSet<>();

    @OneToOne(mappedBy = "users")
    private UserProfile userProfile;

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public Set<Tasks> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Tasks> tasks) {
        this.tasks = tasks;
    }

    // No-argument constructor (required by JPA)
    public User() {
    }

    // Constructor with parameters
    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    // ... Getters
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // ... Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


