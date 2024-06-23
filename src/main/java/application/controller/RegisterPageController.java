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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Instant birthday = null;
        if (birthdayField.getValue() != null) {
            birthday = birthdayField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant();
        }
        String address = addressField.getText();

        // Verificare username, format email, parola, filled fields
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty() || birthday == null || address.isEmpty()) {
            welcomeText.setText("All fields must be filled");
            return;
        }
        if (userService.isUsernameTaken(username)) {
            welcomeText.setText("Username is already taken. Please choose another one.");
            return;
        }
        if (!EmailValidator.isValidEmail(email)) {
            welcomeText.setText("Invalid email format. Please enter a valid email address.");
            return;
        }
        if (!password.equals(confirmPassword)) {
            welcomeText.setText("Passwords do not match");
            return;
        }

        try {
            // Creare instanta UserProfile
            UserProfile userProfile = new UserProfile();
            userProfile.setEmail(email);
            userProfile.setBirthday(birthday);
            userProfile.setAddress(address);

            // Salvam UserProfile
            userProfileService.addUserProfile(userProfile);

            // Creare instanta User
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setIdUserProfile(userProfile);

            // Salvam User
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

    public class EmailValidator {
        // Regex pattern to ensure the email ends with a TLD of 2 or 3 letters
        private static final String EMAIL_PATTERN =
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}$";
        private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

        public static boolean isValidEmail(String email) {
            if (email == null) {
                return false;
            }
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }
    }
}
