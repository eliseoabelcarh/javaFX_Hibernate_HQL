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

}