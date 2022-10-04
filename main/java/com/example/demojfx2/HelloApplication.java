package com.example.demojfx2;

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

import java.io.IOException;

public class HelloApplication extends Application {
    private HelloController controller = new HelloController();
    private Text text;

    @Override
    public void start(Stage stage) throws IOException {
        // Création du lien 1<->1 entre l'IHM courante et le contrôleur.
        this.controller.setIHM(this);

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

        text = new Text("Appui sur");

        MenuHandler openHandler = new MenuHandler(this.controller);
        open.setOnAction(openHandler);

        save.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent actionEvent) {
                //System.out.printf("Appui sur le menu Save.\n");
                HelloApplication.this.controller.appuiSurSave();
            }
        });

        saveAs.setOnAction(actionEvent -> {
          //System.out.printf("Appui sur Save As\n");
            HelloApplication.this.controller.appuiSurSaveAs();
        });

        TableView<Utilisateur> users = new TableView<>();

        TableColumn<Utilisateur, String> genreCol = new TableColumn<>("Genre");
        genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));

        TableColumn<Utilisateur, String> prenomCol = new TableColumn<>("Prenom");
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        prenomCol.setCellFactory(TextFieldTableCell.forTableColumn());
        // Pris en charge par le fait que l'on a ajouté une méthode "prenomProperty()" dans la classe
        // Utilisateur.
        /* prenomCol.setOnEditCommit( event ->{
            System.out.printf("Nouveau prenom: %s", event.getNewValue());
            event.getRowValue().setPrenom(event.getNewValue());
        });*/



        TableColumn<Utilisateur, String> nomCol = new TableColumn<>("Nom");
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nomCol.setCellFactory(TextFieldTableCell.forTableColumn());


        users.getColumns().addAll(genreCol, prenomCol, nomCol);
        users.setEditable(true);
        users.setItems(this.controller.getUsers());

        deleteFirst.setOnAction(actionEvent -> {
            HelloApplication.this.controller.deleteFirstUser();
        });

        dumpUsers.setOnAction(actionEvent -> {
            HelloApplication.this.controller.dumpUsers();
        });




        root.getChildren().addAll(menuBar, text, users);

        Scene scene = new Scene(root);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void setText(String text) {
        this.text.setText("Appui sur: "+text);
    }
}