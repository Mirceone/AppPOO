package com.example.javafxapp;

//import com.example.javafxapp.service.UserService;

import com.example.javafxapp.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.Objects;


public class Controller {

    private SceneController sceneController = new SceneController();

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label welcomeText;

    @FXML
    private void onLoginButtonClick(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if(Objects.equals(username, password)){
            welcomeText.setText("Welcome " + username);
        }

//        UserService userService = new UserService();
//        User user = userService.login(username, password);
//        if (user != null) {
//            // Successful login, navigate to the main application screen
//        } else {
//            // Invalid credentials, show an error message
//        }
    }

    @FXML
    public void onRegisterButtonClick(ActionEvent event) throws IOException {
        sceneController.switchToRegister(event);
    }

    @FXML
    public void onBackButtonClick(ActionEvent event) throws IOException{
        sceneController.switchToLogin(event);
    }
}

