package com.example.javafxapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.io.IOException;


public class MainAppPageController {
    private SceneController sceneController = new SceneController();

    @FXML
    private Label welcomeText;

    public void onLogoutButtonClick(ActionEvent event) throws IOException {
        sceneController.switchToLogin(event);
    }

    public static String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;  // handle null or empty string
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }


    public void setWelcomeMessage(String message) {
        welcomeText.setText(message);
    }

}

