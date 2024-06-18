module com.example.javafxapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.desktop;


    exports com.example.javafxapp;
    exports com.example.javafxapp.controller;
    exports com.example.javafxapp.entity;
    exports com.example.javafxapp.service;

    opens com.example.javafxapp to javafx.fxml;
    opens com.example.javafxapp.controller to javafx.fxml;
    opens com.example.javafxapp.service to javafx.fxml;
    opens com.example.javafxapp.entity;

}