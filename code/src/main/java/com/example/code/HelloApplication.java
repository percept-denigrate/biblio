package com.example.code;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setTitle("Hello!");

        VBox root = new VBox();

        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");
        Menu actions = new Menu("Actions");
        menuBar.getMenus().addAll(file, actions);

        MenuItem open = new MenuItem("Open ...");
        MenuItem save = new MenuItem("Save ...");
        MenuItem saveAs = new MenuItem("Save as ...");

        file.getItems().addAll(open, save, saveAs);

        MenuItem deleteFirst = new MenuItem("Delete first user");
        MenuItem dumpUsers = new MenuItem("Dump users");
        actions.getItems().addAll(deleteFirst, dumpUsers);

        Text text = new Text("Appui sur");

        MenuHandler openHandler = new MenuHandler(this.controller);
        open.setOnAction(openHandler);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
