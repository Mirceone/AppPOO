package com.example.javafxapp.model;

import jakarta.persistence.*;

// ... (JPA annotations and fields representing the 'users' table columns)


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    // No-argument constructor (required by JPA)
    protected User() {
    }

    // Constructor with parameters
    public User(String username, String password) {
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


