package com.example.demojfx2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HelloController {
    private HelloApplication ihm;
    private ObservableList<Utilisateur> users;

    public HelloController(){
        this.users = FXCollections.observableArrayList();
        Utilisateur user1 = new Utilisateur("Tronel", "Frédéric", Genre.M);
        Utilisateur user2 = new Utilisateur("Lalande", "Jean-François", Genre.M);
        Utilisateur user3 = new Utilisateur("Viet Triem Tong", "Valérie", Genre.Mme);
        this.users.addAll(user1, user2, user3);
    }

    public HelloController(HelloApplication ihm){
        this();
        this.ihm = ihm;
    }

    public void setIHM(HelloApplication ihm){
        this.ihm = ihm;
    }

    public void appuiSurOpen(){
        System.out.printf("Appui sur Open.\n");
        this.ihm.setText("Open");
    }

    public void appuiSurSave() {
        System.out.printf("Appui sur Save.\n");
        this.ihm.setText("Save");
    }

    public void appuiSurSaveAs() {
        System.out.printf("Appui sur Save As.\n");
        this.ihm.setText("Save As");
    }

    public ObservableList<Utilisateur> getUsers(){
        return  this.users;
    }

    public void deleteFirstUser() {
        if(this.users.size()>0)
            this.users.remove(0);
    }

    public void dumpUsers() {
        for(Utilisateur u: users)
            System.out.println(u);
    }
}