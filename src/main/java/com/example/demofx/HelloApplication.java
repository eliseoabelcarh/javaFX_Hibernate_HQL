package com.example.demofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        String css = this.getClass().getResource("appStyles.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static int createRandomNumber() {
        int MIN = 1;
        int MAX = 999999;
        return (int) ((Math.random() * (MAX - MIN)) + MIN);
    }

    public static void main(String[] args) {


        DAOhql dao = DAOhql.getInstance();
        UsersEntity user = dao.getUserById(123);
        System.out.println("finded Id: " + user.getId());
        System.out.println("finded Name: " + user.getNombre());

        UsersEntity newUser = new UsersEntity();
        newUser.setNombre("New User".concat(String.valueOf(createRandomNumber())));
        dao.addNewUser(newUser);
        System.out.println("New User Name: " + newUser.getNombre());


        List<UsersEntity> usersList = dao.getAllUsers();
        for (UsersEntity user1 : usersList) {
            System.out.println("Id: " + user1.getId());
            System.out.println("Name: " + user1.getNombre());
        }


        dao.close();
        launch();
    }
}