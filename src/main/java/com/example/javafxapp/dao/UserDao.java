package com.example.javafxapp.dao;

import com.example.javafxapp.User;

public interface UserDao extends GenericDao<User> { // Inherits generic CRUD operations

    // User-specific methods
    User findByUsernameAndPassword(String username, String password);
    // ... other user-related methods
}
