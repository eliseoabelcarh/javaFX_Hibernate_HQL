package com.example.demofx.controllers;

import com.example.demofx.HelloApplication;
import com.example.demofx.dao.DAOhql;
import com.example.demofx.models.UsersEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label errorText;
    @FXML
    private TextField inputUsername;
    @FXML
    private TextField inputPassword;


    @FXML
    protected void onHelloButtonClick() throws IOException {
        String username = inputUsername.getText();
        String password = inputPassword.getText();
        DAOhql dao = DAOhql.getInstance();
        UsersEntity userDB = dao.getUserByUsername(username);
        if (userDB != null) {
            if (userDB.getPassword().equals(password)) {
                HelloApplication.changeScene("Home.fxml");
            } else {
                errorText.setText("Wrong password");
            }
        } else {
            errorText.setText("User not found");
        }
    }
}