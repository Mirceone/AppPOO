package application.controller;

import application.entity.User;
import application.service.UserProfileService;
import application.service.UserService;
import application.session.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Objects;
import java.util.Optional;

public class SettingsWindowController {

    private UserProfileService userProfileService;
    private UserService userService;
    private SessionManager sessionManager;
    private SceneController sceneController = new SceneController();

    @FXML
    private TextField newUsernameField;
    @FXML
    private Label errorMessage;

    public SettingsWindowController() {
        this.sessionManager = SessionManager.getInstance();
    }

    @FXML
    private void initialize() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaFxTest");
        userProfileService = new UserProfileService(emf);
        userService = new UserService(emf);
    }

    @FXML
    public void onChangeUsername(ActionEvent event) {
        String newUsername = newUsernameField.getText();
        handleChangeUsername(newUsername);
    }

    @FXML
    private void handleChangeUsername(String newUsername) {
        if (sessionManager == null) {
            errorMessage.setText("Session manager not initialized.");
            return;
        }

        User currentUser = sessionManager.getCurrentUser();
        if (currentUser == null) {
            errorMessage.setText("No user logged in.");
            return;
        }

        if (newUsername == null || newUsername.trim().isEmpty()) {
            errorMessage.setText("Username cannot be empty.");
            return;
        }

        if (!Objects.equals(newUsername, currentUser.getUsername())) {
            if (!userService.isUsernameTaken(newUsername)) {
                currentUser.setUsername(newUsername);
                userService.updateUser(currentUser);
                System.out.println("Username updated successfully to: " + newUsername);
                errorMessage.setText("Username updated successfully to: " + newUsername);
                newUsernameField.setText("");
            } else {
                errorMessage.setText("Username already taken");
            }
        } else {
            errorMessage.setText("New username cannot be the same as the old username");
        }
    }

    @FXML
    public void onDeleteAccount(ActionEvent event) {
        handleDeleteAccount();
    }

    @FXML
    private void handleDeleteAccount() {
        if (sessionManager == null) {
            errorMessage.setText("Session manager not initialized.");
            return;
        }

        User currentUser = sessionManager.getCurrentUser();
        if (currentUser == null) {
            errorMessage.setText("No user logged in.");
            return;
        }

        // Ask for confirmation before deleting the account
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Account Confirmation");
        alert.setHeaderText("Confirm Account Deletion");
        alert.setContentText("Are you sure you want to delete your account? This action cannot be undone.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Perform deletion logic
            try {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaFxTest");
                UserService userService = new UserService(emf);
                userService.deleteUser(currentUser);

                // Clear the current user session
                sessionManager.clearCurrentUser();
                System.out.println("Account deleted successfully.");

            } catch (Exception e) {
                e.printStackTrace();
                errorMessage.setText("Error deleting account.");
            }
        }
    }
}
