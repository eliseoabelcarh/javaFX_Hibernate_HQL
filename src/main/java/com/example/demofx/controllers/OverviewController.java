package com.example.demofx.controllers;

import com.example.demofx.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OverviewController implements Initializable {

    @FXML
    private VBox pnItems = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Add items list
        Node[] nodes = new Node[10];
        for (int i = 0; i < nodes.length; i++) {
            final int j = i;
            try {
                nodes[i] = FXMLLoader.load(HelloApplication.class.getResource("Item.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //give the items some effect
            nodes[i].setOnMouseEntered(event -> {
                nodes[j].setStyle("-fx-background-color : #0A0E3F");
            });
            nodes[i].setOnMouseExited(event -> {
                nodes[j].setStyle("-fx-background-color : #02030A");
            });
            pnItems.getChildren().add(nodes[i]);
        }


    }
}
