module com.example.demofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires jakarta.persistence;
    requires java.persistence;


    opens com.example.demofx to javafx.fxml, org.hibernate.orm.core;
    exports com.example.demofx;
    exports com.example.demofx.dao;
    opens com.example.demofx.dao to javafx.fxml, org.hibernate.orm.core;
    exports com.example.demofx.models;
    opens com.example.demofx.models to javafx.fxml, org.hibernate.orm.core;
    exports com.example.demofx.controllers;
    opens com.example.demofx.controllers to javafx.fxml, org.hibernate.orm.core;

}