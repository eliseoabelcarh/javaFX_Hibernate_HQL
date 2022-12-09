package com.example.demofx;

import com.example.demofx.dao.DAOhql;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class HelloApplication extends Application {

    private static Stage primaryStage;



    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
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
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        //allow drag
        allowDrag(pane, primaryStage);
        //Force Center the stage
        centerStage(primaryStage);
    }

    private static void centerStage(Stage stage) {
        List<Screen> screensForRectangle = Screen.getScreensForRectangle(stage.getX(), stage.getY(), stage.getWidth(), stage.getHeight());
        Screen screen = screensForRectangle.isEmpty() ? Screen.getPrimary() : screensForRectangle.get(0);
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX() + (bounds.getWidth() - stage.getWidth()) / 2);
        stage.setY(bounds.getMinY() + (bounds.getHeight() - stage.getHeight()) / 2);
    }

    private static void allowDrag(Parent pane, Stage stage) {
        AtomicReference<Double> x = new AtomicReference<>((double) 0);
        AtomicReference<Double> y = new AtomicReference<>((double) 0);
        pane.setOnMousePressed(event -> {
            x.set(event.getSceneX());
            y.set(event.getSceneY());
        });
        pane.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x.get());
            stage.setY(event.getScreenY() - y.get());
        });
    }

    public static int createRandomNumber() {
        int MIN = 1;
        int MAX = 999999;
        return (int) ((Math.random() * (MAX - MIN)) + MIN);
    }

    public static void main(String[] args) {


//        DAOhql dao = DAOhql.getInstance();
//        UsersEntity user = dao.getUserById(123);
//        if(user != null) {
//            System.out.println("finded Id: " + user.getId());
//            System.out.println("finded Name: " + user.getUsername());
//        }else{
//            System.out.println("user not finded");
//        }
//
//
//
//
//        List<UsersEntity> usersList = dao.getAllUsers();
//        if(usersList.size() > 0) {
//            for (UsersEntity userEntity : usersList) {
//                System.out.println("User Name: " + userEntity.getUsername());
//            }
//        }else {
//            System.out.println("No users found");
//        }
//
//        UsersEntity newUser = new UsersEntity();
//        newUser.setUsername("User".concat(String.valueOf(createRandomNumber())));
//        newUser.setPassword("123456");
//        dao.addNewUser(newUser);
//        System.out.println("New User Name: " + newUser.getUsername());
//
//
//
//        dao.close();
        launch();
    }

    @Override
    public void stop() {
        System.out.println("Stage and Dao is closing");
        DAOhql.getInstance().close();
    }

}