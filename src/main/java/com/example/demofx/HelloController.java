package com.example.demofx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws IOException {

        welcomeText.setText("Welcome to JavaFX Application!");
        //setTimeout for 5 seconds
        HelloApplication.changeScene("Home.fxml");
    }
}