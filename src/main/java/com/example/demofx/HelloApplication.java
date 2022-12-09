package com.example.demofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {

    private static Stage primaryStage;

    private static double x;
    private static double y;
    @Override
    public void start(Stage stage) throws IOException {

        this.primaryStage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        String css = this.getClass().getResource("appStyles.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void changeScene(String fxml) throws IOException {
        //UI resource: https://github.com/k33ptoo/RestaurantMgtSampleUI
        Parent pane = FXMLLoader.load(HelloApplication.class.getResource(fxml));
        Scene scene = new Scene( pane );
        primaryStage.setScene(scene);
        //drag it here
        pane.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        pane.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);
        });
        //Force Center the stage
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
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