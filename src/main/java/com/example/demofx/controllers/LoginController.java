package com.example.demofx.controllers;

import com.example.demofx.MainApplication;
import com.example.demofx.dao.DAOUsersHql;
import com.example.demofx.dao.DAOFactory;
import com.example.demofx.global.UserHolder;
import com.example.demofx.models.UsersEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {
    @FXML
    private Label errorText;
    @FXML
    private TextField inputUsername;
    @FXML
    private TextField inputPassword;

    private final String WRONG_PASSWORD = "Wrong password";
    private final String USER_NOT_FOUND = "User not found";

    @FXML
    protected void onLoginButtonClick() throws IOException {
        String username = inputUsername.getText();
        String password = inputPassword.getText();
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOUsersHql dao = daoFactory.getUsersDao();
        UsersEntity userDB = dao.getUserByUsername(username);
        if (userDB != null) {
            if (userDB.getPassword().equals(password)) {
                UserHolder.getInstance().setUsername(username);
                MainApplication.changeScene("home-view.fxml");
            } else {
                errorText.setText(WRONG_PASSWORD);
            }
        } else {
            errorText.setText(USER_NOT_FOUND);
        }
    }
}