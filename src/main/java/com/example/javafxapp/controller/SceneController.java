package com.example.javafxapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class SceneController {
    private Stage stage;
    private Scene scene;

    public void switchToRegister(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/com/example/javafxapp/registerPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader);
        stage.setScene(scene);
        stage.setTitle("Register");
        stage.show();

    }

    public void switchToLogin(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/com/example/javafxapp/loginPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }

    public void switchToMainPage(ActionEvent event, String username) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javafxapp/mainAppPage.fxml"));
        Parent root = loader.load();

        // Get the controller associated with the loaded FXML file
        MainAppPageController controller = loader.getController();

        // Set the welcome message using the controller
        controller.setWelcomeMessage("Welcome " + username + "!");

        // Initialize Scene and Stage
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("To-Do APP");
        stage.show();
    }

}
