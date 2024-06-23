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
    private double previousWidth;
    private double previousHeight;

    public void onSettingsButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/settingsWindow.fxml"));
            Parent root = loader.load();

            // Access the controller to set any initial data or configurations
            SettingsWindowController settingsWindowController = loader.getController();
            // Example: settingsWindowController.setUsername(SessionManager.getInstance().getCurrentUser().getUsername());

            Stage settingsStage = new Stage();
            settingsStage.initModality(Modality.APPLICATION_MODAL);
            settingsStage.setTitle("Settings");
            settingsStage.setScene(new Scene(root));
            settingsStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToRegister(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/application/registerPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        saveSceneDimensions();
        scene = new Scene(fxmlLoader, previousWidth, previousHeight);
        setStage(scene, "Register");
    }

    public void switchToLogin(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/application/loginPage.fxml"));
        stage = getStageFromEvent(event);
        saveSceneDimensions();
        scene = new Scene(fxmlLoader, previousWidth, previousHeight);
        setStage(scene, "Login");
    }

    public void switchToMainPage(ActionEvent event, String username) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/mainAppPage.fxml"));
        Parent root = loader.load();
        MainAppPageController controller = loader.getController();
        controller.setWelcomeMessage("Welcome " + username + "!");

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        saveSceneDimensions();
        scene = new Scene(root, previousWidth, previousHeight);
        setStage(scene, "To-Do APP");
    }

    public void showAddTaskWindow() throws IOException {
        Parent fxmlloader = FXMLLoader.load(getClass().getResource("/application/AddTaskWindow.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add Task");
        stage.setScene(new Scene(fxmlloader));
        stage.showAndWait();
    }

    private Stage getStageFromEvent(ActionEvent event) {
        if (event.getSource() instanceof Node) {
            return (Stage) ((Node) event.getSource()).getScene().getWindow();
        } else if (event.getSource() instanceof MenuItem) {
            return (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        }
        return null;
    }

    private void saveSceneDimensions() {
        if (stage != null) {
            previousWidth = stage.getWidth();
            previousHeight = stage.getHeight();
        }
    }

    private void setStage(Scene scene, String title) {
        if (stage != null) {
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        }
    }
}
