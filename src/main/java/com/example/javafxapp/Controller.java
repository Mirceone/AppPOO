package com.example.javafxapp;

//import com.example.javafxapp.service.UserService;

import com.example.javafxapp.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.Objects;


public class Controller {

    private SceneController sceneController = new SceneController();

    @FXML
    private Label registerText;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField confirmPasswordField;

    @FXML
    private Label welcomeText;

    @FXML
    private TextField emailField;

    @FXML
    private DatePicker birthdayField;

    @FXML
    private TextField addressField;



    @FXML
    private void onLoginButtonClick(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if(Objects.equals(username, password)){
            welcomeText.setText("Welcome " + username);
        }

    }

    @FXML
    public void onRegisterButtonClick(ActionEvent event) throws IOException {
        sceneController.switchToRegister(event);
    }

    @FXML
    public void onBackButtonClick(ActionEvent event) throws IOException{
        sceneController.switchToLogin(event);
    }

    public void onRegisterButtonClick1(ActionEvent event) throws IOException {
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        if(Objects.equals(password, confirmPassword)){
            welcomeText.setText("Welcome " + usernameField.getText());
        }
    }
}

