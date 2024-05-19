package com.example.javafxapp.service;

import com.example.javafxapp.User;
import com.example.javafxapp.dao.UserDao;

public class UserService {
    private UserDao userDao;

    // ... Constructor injection (or other DI mechanism)

    public User login(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }

    // ... other user-related service methods
}
