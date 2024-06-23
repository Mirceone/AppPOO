package application.controller;

import java.util.ArrayList;
import java.util.List;

import application.service.UserService;
import application.entity.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserController {

    @FXML
    public ListView<User> userListView;

    private EntityManagerFactory entityManagerFactory;
    private UserService userService;

    @FXML
    void initialize() {
        // Initialize EntityManagerFactory and UserService
        entityManagerFactory = Persistence.createEntityManagerFactory("JavaFxTest");
        userService = new UserService(entityManagerFactory);

        List<User> allUsers = userService.getAllUsers();

        System.out.println(allUsers);
        userListView.setItems(FXCollections.observableArrayList(new ArrayList<>(allUsers)));

        // test login
        try {
            System.out.println(userService.findUser("test", "1234").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
