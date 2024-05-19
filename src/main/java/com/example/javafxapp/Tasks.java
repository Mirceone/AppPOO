package com.example.javafxapp;
import jakarta.persistence.*;

// ... (JPA annotations and fields representing the 'tasks' table columns)


@Entity
@Table(name = "tasks")
public class Tasks {
    // ... (attributes like id, title, description, dueDate, etc.)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    // ... (constructors, getters, setters, etc.)
    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
