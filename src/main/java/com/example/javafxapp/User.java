package com.example.javafxapp;

import jakarta.persistence.*;

// ... (JPA annotations and fields representing the 'users' table columns)


@Entity
@Table(name = "users")
public class User {
    // ... (attributes like id, name, email, etc.)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    // ... (constructors, getters, setters, etc.)
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Setters
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

