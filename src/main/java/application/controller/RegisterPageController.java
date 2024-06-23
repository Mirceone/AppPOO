package application.controller;

import application.entity.User;
import application.entity.UserProfile;
import application.service.UserProfileService;
import application.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;

public class RegisterPageController {

    private final SceneController sceneController = new SceneController();
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JavaFxTest");
    private final UserService userService = new UserService(entityManagerFactory);
    private final UserProfileService userProfileService = new UserProfileService(entityManagerFactory);

    @FXML
    private Label welcomeText;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField confirmPasswordField;

    @FXML
    private TextField emailField;

    @FXML
    private DatePicker birthdayField;

    @FXML
    private TextField addressField;

    @FXML
    public void onRegisterButtonClick(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String email = emailField.getText();
        Instant birthday = birthdayField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant();
        String address = addressField.getText();

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty() || birthday == null || address.isEmpty()) {
            welcomeText.setText("All fields must be filled");
            return;
        }

        if (!password.equals(confirmPassword)) {
            welcomeText.setText("Passwords do not match");
            return;
        }

        try {
            // Create UserProfile object
            UserProfile userProfile = new UserProfile();
            userProfile.setEmail(email);
            userProfile.setBirthday(birthday);
            userProfile.setAddress(address);

            // Save UserProfile
            userProfileService.addUserProfile(userProfile);

            // Create User object
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setIdUserProfile(userProfile);

            // Save User
            userService.addUser(user);

            welcomeText.setText("Registration successful!");
        } catch (Exception e) {
            e.printStackTrace();
            welcomeText.setText("An error occurred during registration");
        }
        usernameField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
        emailField.setText("");
        birthdayField.setValue(null);
        addressField.setText("");
    }

    @FXML
    public void onBackButtonClick(ActionEvent event) throws IOException {
        sceneController.switchToLogin(event);
    }
}
