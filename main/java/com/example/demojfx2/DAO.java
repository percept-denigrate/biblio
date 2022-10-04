package com.example.demojfx2;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class DAO implements ListChangeListener<Utilisateur>, InvalidationListener {
    private ObservableList<Utilisateur> users;

    public DAO(ObservableList<Utilisateur> users){
        this.users = users;
        this.users.addListener(this::onChanged);
        this.users.addListener(this::invalidated);
    }
    
    @Override
    public void onChanged(Change<? extends Utilisateur> change) {

    }

    @Override
    public void invalidated(Observable observable) {

    }
}
