module com.example.javafxapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.sql;


    opens com.example.javafxapp to javafx.fxml;
    exports com.example.javafxapp;
    exports com.example.javafxapp.model;
    opens com.example.javafxapp.model to javafx.fxml, org.hibernate.orm.core;


}