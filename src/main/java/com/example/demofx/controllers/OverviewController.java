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
    private Label order_price;
    @FXML
    private List<OrdersProductsEntity> showed_list;

    private List<OrdersProductsEntity> ordersProducts;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        reset_search_btn.setVisible(false);

        getDataFromDAO();

        loadActionEvents();

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

    public void loadActionEvents() {
        search_icon.setOnMouseClicked(event -> {
            reset_search_btn.setVisible(true);
            String searched_text = search_field.getText();
            showed_list = showed_list.stream().filter(o -> o.getId().getUniqueId().equals(searched_text)).collect(Collectors.toList());
            loadItemsView(showed_list);
        });
    }

    public void getDataFromDAO() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOOrdersProductsHql dao = daoFactory.getOrdersProductsDao();
        ordersProducts = dao.getAllOrdersProducts();
        total_orders.setText(String.valueOf(ordersProducts.size()));
        showed_list = ordersProducts;
    }

    public void loadItemsView(List<OrdersProductsEntity> ordersProducts) {
        pnItems.getChildren().clear();
        Double total_price = 0.0;
        for (OrdersProductsEntity item : ordersProducts) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(MainApplication.class.getResource("item-view.fxml"));
            try {
                Node node = fxmlLoader.load();
                ItemController itemController = fxmlLoader.getController();
                itemController.setData(item);
                pnItems.getChildren().add(node);
                total_price += item.getPrice();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        order_price.setText(String.valueOf(total_price));
    }
}
