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

        //Product 1
        ProductsEntity  product1 = new ProductsEntity();
        product1.setId(createRandomNumber());
        product1.setProductName("Product".concat(String.valueOf(createRandomNumber())));
        product1.setProductSku(String.valueOf(createRandomNumber()));
        product1.setProductEpc(String.valueOf(createRandomNumber()));
        product1.setProductPrice(150.0);

        //Product 2
        ProductsEntity  product2 = new ProductsEntity();
        product2.setId(createRandomNumber());
        product2.setProductName("Product".concat(String.valueOf(createRandomNumber())));
        product2.setProductSku(String.valueOf(createRandomNumber()));
        product2.setProductEpc(String.valueOf(createRandomNumber()));
        product2.setProductPrice(200.0);


        //Saving products in DB
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOProductsHql daoProductsHql = daoFactory.getProductsDao();
        daoProductsHql.addNewProduct(product1);
        daoProductsHql.addNewProduct(product2);

        //Init order
        OrdersEntity order = new OrdersEntity();
        order.setId(createRandomNumber());//tested with 10
        DAOOrdersHql daoOrdersHql = daoFactory.getOrdersDao();
        daoOrdersHql.addNewOrder(order);

        //Add items to order
        OrdersProductsEntity orderProduct1 = new OrdersProductsEntity(order, product1,2);
        OrdersProductsEntity orderProduct2 = new OrdersProductsEntity(order, product2,3);
        DAOOrdersProductsHql daoOrdersProductsHql = daoFactory.getOrdersProductsDao();
        daoOrdersProductsHql.addNewOrderProduct(orderProduct1);
        daoOrdersProductsHql.addNewOrderProduct(orderProduct2);

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