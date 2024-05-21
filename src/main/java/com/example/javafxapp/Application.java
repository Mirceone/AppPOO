package com.example.javafxapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Map;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        Scene scene = new Scene(fxmlLoader);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }

    public static void testDBConnection() {
        // Create an EntityManagerFactory to read the persistence.xml configuration
        System.out.println("testDBConnection");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaFxTest");
        Map<String, Object> properties = emf.getProperties();

        // Retrieve the JDBC URL, username, and password from the EntityManagerFactory properties
        String url = (String) properties.get("jakarta.persistence.jdbc.url");
        String user = (String) properties.get("jakarta.persistence.jdbc.user");
        String password = (String) properties.get("jakarta.persistence.jdbc.password");
        System.out.println("url: " + url);
        System.out.println("user: " + user);
        System.out.println("password: " + password);

        // Test the database connection using the retrieved properties
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {
                System.out.println("Connected to the database!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Close the EntityManagerFactory
        emf.close();
    }

    public static void main(String[] args) {
//        launch();
        testDBConnection();
    }
}