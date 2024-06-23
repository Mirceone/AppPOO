package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.MenuItem;

import java.io.IOException;

public class SceneController {
    private Stage stage;
    private Scene scene;

    public void switchToRegister(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/application/registerPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader);
        stage.setScene(scene);
        stage.setTitle("Register");
        stage.show();
    }

    public void switchToLogin(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/application/loginPage.fxml"));
        // Determinam daca este apelat de logout sau back button
        if (event.getSource() instanceof Node) {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        } else if (event.getSource() instanceof MenuItem) {
            stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        }
        scene = new Scene(fxmlLoader);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }

    public void switchToMainPage(ActionEvent event, String username) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/mainAppPage.fxml"));
        Parent root = loader.load();
        MainAppPageController controller = loader.getController();
        controller.setWelcomeMessage("Welcome " + username + "!");

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("To-Do APP");
        stage.show();
    }

    public void showAddTaskWindow() throws IOException {
            Parent fxmlloader = FXMLLoader.load(getClass().getResource("/application/AddTaskWindow.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add Task");
            stage.setScene(new Scene(fxmlloader));
            stage.showAndWait();
    }
}
