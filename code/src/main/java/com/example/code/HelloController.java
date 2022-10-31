package com.example.code;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField emailField;
    @FXML
    private String email;
    @FXML
    public void connecter(ActionEvent event) throws IOException {
        email = emailField.getText();
        System.out.println(email);
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("appli.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}