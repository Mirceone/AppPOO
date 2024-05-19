module com.example.javafxapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;


    opens com.example.javafxapp to javafx.fxml;
    exports com.example.javafxapp;
}