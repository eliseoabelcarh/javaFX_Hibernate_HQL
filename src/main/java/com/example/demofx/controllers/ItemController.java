package com.example.demofx.controllers;

import com.example.demofx.models.OrdersProductsEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ItemController {

    @FXML
    private Label product_qty;
    @FXML
    private Label product_name;

    @FXML
    private Label product_sku;

    @FXML
    private Label product_epc;

    @FXML
    private Label product_price;

    private OrdersProductsEntity item;

    public void setData(OrdersProductsEntity _item){
        this.item = _item;
        product_qty.setText(String.valueOf(item.getQty()));
        product_name.setText(item.getProductsByProductId().getProductName());
        product_sku.setText(item.getProductsByProductId().getProductSku());
        product_epc.setText(item.getProductsByProductId().getProductEpc());
        product_price.setText(item.getProductsByProductId().getProductPrice().toString());

    }

}
