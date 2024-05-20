module com.example.javafxapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires java.persistence;


    opens com.example.javafxapp to javafx.fxml;
    exports com.example.javafxapp;
    exports com.example.javafxapp.model;
    opens com.example.javafxapp.model to javafx.fxml;
}