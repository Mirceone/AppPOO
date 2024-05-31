module com.example.javafxapp {
    requires javafx.controls;
    requires javafx.fxml;
//    requires jakarta.persistence;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.persistence;


    opens com.example.javafxapp to javafx.fxml;
    exports com.example.javafxapp;
    exports com.example.javafxapp.model;
    opens com.example.javafxapp.model to javafx.fxml, org.hibernate.orm.core;


}