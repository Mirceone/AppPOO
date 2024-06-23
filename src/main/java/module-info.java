module com.example.javafxapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.desktop;


    exports application;
    exports application.controller;
    exports application.entity;
    exports application.service;

    opens application to javafx.fxml;
    opens application.controller to javafx.fxml;
    opens application.service to javafx.fxml;
    opens application.entity;
}