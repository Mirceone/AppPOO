package com.example.javafxapp.controller;

import com.example.javafxapp.entity.User;
import com.example.javafxapp.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

import static com.example.javafxapp.controller.MainAppPageController.capitalizeFirstLetter;

public class LoginPageController {

    private SceneController sceneController = new SceneController();
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JavaFxTest");
    private UserService userService = new UserService(entityManagerFactory);

    @FXML
    private Label welcomeText;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    public void onLoginButtonClick(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

//        try {
//            userService.findUser(username, password);
//            sceneController.switchToMainPage(event, username);
//        } catch (Exception e) {
//            welcomeText.setText("Invalid username or password");
//        }
        // Verificare user si pass fields daca sunt goala inainte de a cauta in db
        if (username.isEmpty() || password.isEmpty()) {
            welcomeText.setText("Username or password can't be empty");
            return;
        }

        try {
            User user = userService.findUser(username, password);
            sceneController.switchToMainPage(event, capitalizeFirstLetter(user.getUsername()));
        } catch (Exception e) {
            String errorMessage = e.getMessage(); // Get the exception message
            welcomeText.setText(errorMessage);
        }
    }

    @FXML
    public void onRegisterButtonClick(ActionEvent event) throws IOException {
        sceneController.switchToRegister(event);
    }
}
