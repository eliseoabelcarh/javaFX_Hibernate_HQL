package com.example.demofx;

import com.example.demofx.dao.*;
import com.example.demofx.models.OrdersEntity;
import com.example.demofx.models.OrdersProductsEntity;
import com.example.demofx.models.ProductsEntity;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class MainApplication extends Application {

    private static Stage primaryStage;


    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        String css = Objects.requireNonNull(this.getClass().getResource("styles/appStyles.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Login User!");
        stage.setScene(scene);
        stage.show();

    }
    public static void changeScene(String fxml) throws IOException {
        //UI resource: https://github.com/k33ptoo/RestaurantMgtSampleUI
        Parent pane = FXMLLoader.load(Objects.requireNonNull(MainApplication.class.getResource(fxml)));
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
        int min = 1;
        int max = 9999999;
        return (int) ((Math.random() * (max - min)) + min);
    }
    public static void main(String[] args) {


//        DAOUsersHql dao = DAOUsersHql.getInstance();
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



        ProductsEntity  product = new ProductsEntity();
        product.setId(createRandomNumber());
        product.setProductName("Product".concat(String.valueOf(createRandomNumber())));
        product.setProductSku(String.valueOf(createRandomNumber()));
        product.setProductEpc(String.valueOf(createRandomNumber()));
        product.setProductPrice(150.0);

        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOProductsHql daoProductsHql = daoFactory.getProductsDao();
        daoProductsHql.addNewProduct(product);

        OrdersEntity order = new OrdersEntity();
        order.setId(createRandomNumber());
        DAOOrdersHql daoOrdersHql = daoFactory.getOrdersDao();
        daoOrdersHql.addNewOrder(order);

        OrdersProductsEntity orderProduct = new OrdersProductsEntity(order, product,2);
        DAOOrdersProductsHql daoOrdersProductsHql = daoFactory.getOrdersProductsDao();
        daoOrdersProductsHql.addNewOrderProduct(orderProduct);

        launch();
    }

    @Override
    public void stop() {
        System.out.println("Stage and Dao is closing");
        DAOUsersHql.getInstance().close();
        DAOProductsHql.getInstance().close();
        DAOOrdersHql.getInstance().close();
        DAOOrdersProductsHql.getInstance().close();
    }

}