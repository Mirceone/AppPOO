package com.example.javafxapp.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.javafxapp.entity.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import com.example.javafxapp.service.UserService;

public class UserController {
    @FXML
    public ListView<User> userListView;

    @FXML
    void initialize() {
        UserService userService = new UserService();
        List<User> allUsers = userService.getAllUsers();

        System.out.println(allUsers);
        userListView.setItems(FXCollections.observableArrayList(new ArrayList<User>(allUsers)));

        // test login
        try {
            System.out.println(userService.findUser("test", "1234").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
