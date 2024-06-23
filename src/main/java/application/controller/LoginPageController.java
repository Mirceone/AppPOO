package application.controller;

import application.entity.User;
import application.service.UserService;
import application.session.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

public class LoginPageController {

    private final SceneController sceneController = new SceneController();
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JavaFxTest");
    private final UserService userService = new UserService(entityManagerFactory);

    @FXML
    private Label welcomeText;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    public void onLoginButtonClick(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Verificare user si pass fields daca sunt goala inainte de a cauta in db
        if (username.isEmpty() || password.isEmpty()) {
            welcomeText.setText("Username or password can't be empty");
            return;
        }

        try {
            User user = userService.findUser(username, password);
            SessionManager.getInstance().setCurrentUser(user);
            sceneController.switchToMainPage(event, capitalizeFirstLetter(user.getUsername()));
        } catch (Exception e) {
            String errorMessage = e.getMessage(); // Get the exception message
            welcomeText.setText(errorMessage);
        }
    }

    public void onRegisterButtonClick(ActionEvent event) throws IOException {
        sceneController.switchToRegister(event);
    }

    public static String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;  // handle null or empty string
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
