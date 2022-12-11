package com.example.demofx.controllers;

import com.example.demofx.MainApplication;
import com.example.demofx.dao.DAOFactory;
import com.example.demofx.dao.DAOOrdersProductsHql;
import com.example.demofx.models.OrdersProductsEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class OverviewController implements Initializable {

    @FXML
    private VBox pnItems = null;

    @FXML
    private Label total_orders;

    @FXML
    private TextField search_field;

    @FXML
    private ImageView search_icon;

    @FXML
    private Button reset_search_btn;
    @FXML
    private List<OrdersProductsEntity> showed_list;

    private List<OrdersProductsEntity> ordersProducts;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        reset_search_btn.setVisible(false);

        //Get Data from DB
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOOrdersProductsHql dao = daoFactory.getOrdersProductsDao();
        ordersProducts = dao.getAllOrdersProducts();
        System.out.println(ordersProducts.size());
        showed_list = ordersProducts;

        total_orders.setText(String.valueOf(ordersProducts.size()));

        search_icon.setOnMouseClicked(event -> {
            reset_search_btn.setVisible(true);
            pnItems.getChildren().removeAll();
            System.out.println("Search");
            String searched_text = search_field.getText();
            System.out.println(searched_text);

            // filter showed_list by searched_text

            showed_list = showed_list.stream()
                    .filter(o -> o.getId().getUniqueId().equals(searched_text)).collect(Collectors.toList());
            System.out.println("SearchEnd" + showed_list.size());
            loadItemsView(showed_list);
        });

        loadItemsView(showed_list);

    }


    @FXML
    public void onClickResetButton() {
        reset_search_btn.setVisible(false);
        search_field.setText("");
        pnItems.getChildren().clear();
        showed_list = ordersProducts;
        loadItemsView(showed_list);
    }
    public void loadItemsView(List<OrdersProductsEntity> ordersProducts)  {
        pnItems.getChildren().clear();
        for (OrdersProductsEntity item : ordersProducts) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(MainApplication.class.getResource("item-view.fxml"));
            try {
                Node node = fxmlLoader.load();
                ItemController itemController = fxmlLoader.getController();
                itemController.setData(item);
                pnItems.getChildren().add(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //        //Add items list
//        Node[] nodes = new Node[ordersProducts.size()];
//        for (int i = 0; i < nodes.length; i++) {
//            final int j = i;
//            try {
//
//                FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("item-view.fxml"));
//                nodes[i] = loader.load();
//                ItemController itemController = loader.getController();
//                String s =Integer.toString(i);
//                OrdersProductsEntity orderProduct = ordersProducts.get(i);
//                System.out.println(orderProduct);
//                itemController.setData(orderProduct);
//
//
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            //give the items some effect
//            nodes[i].setOnMouseEntered(event -> {
//                nodes[j].setStyle("-fx-background-color : #0A0E3F");
//            });
//            nodes[i].setOnMouseExited(event -> {
//                nodes[j].setStyle("-fx-background-color : #02030A");
//            });
//            pnItems.getChildren().add(nodes[i]);
//        }
    }
}
