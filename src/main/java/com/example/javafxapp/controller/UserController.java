package com.example.javafxapp.controller;
import com.example.javafxapp.User;
import com.example.javafxapp.service.UserService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


// ... (Assume @FXML annotations for UI elements)

public class UserController {
    private UserService userService;

    // ... Constructor injection (or other DI mechanism)

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    @FXML
    private void onLoginButtonClick(ActionEvent event) {

        String username = usernameField.getText();
        String password = passwordField.getText();

        User user = userService.login(username, password);
        if (user != null) {
            // Successful login, navigate to the main application screen
        } else {
            // Invalid credentials, show an error message
        }
    }
}

