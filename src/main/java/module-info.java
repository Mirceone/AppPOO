module com.example.javafxapp {
    requires javafx.controls;
    requires javafx.fxml;
//    requires jakarta.persistence;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.persistence;


    exports com.example.javafxapp;
    opens com.example.javafxapp to javafx.fxml;
    exports com.example.javafxapp.controller;
    opens com.example.javafxapp.controller to javafx.fxml;
    opens com.example.javafxapp.entity;
    exports com.example.javafxapp.entity;
    exports com.example.javafxapp.service;
    opens com.example.javafxapp.service to javafx.fxml;

}