package com.example.javafxapp.service;

import java.util.List;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import com.example.javafxapp.dao.UserDao;
import com.example.javafxapp.model.User;

public class UserService {

    private UserDao userDao;

    public UserService() {
        try {
            userDao = new UserDao(Persistence.createEntityManagerFactory("JavaFxTest"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addUser(User newUser) {
        userDao.create(newUser);
    }

    public void updateUser(User updatedUser) {
        userDao.update(updatedUser);
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    // for login
    public User findUser(String user, String pass) throws Exception {
        List<User> users = userDao.find(user);
        if (users.size() == 0) {
            throw new Exception("User not found!");
        }
        User u = users.get(0);

        if (!pass.equals(u.getPassword())) { // Use equals instead of compareTo for string comparison
            throw new Exception("Password does not match");
        }
        return u;
    }
}
