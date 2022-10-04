package com.example.demojfx2;

import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Utilisateur {
    private StringProperty nom;
    private StringProperty prenom;
    private ObjectProperty<Genre>  genre;

    public String getNom() {
        return nom.get();
    }

    public StringProperty nomProperty() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public StringProperty prenomProperty() {
        return prenom;
    }

    public Genre getGenre() {
        return genre.get();
    }

    public ObjectProperty<Genre> genreProperty() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre.set(genre);
    }

    public Utilisateur(String nom, String prenom, Genre genre){
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.genre = new SimpleObjectProperty<Genre>(genre);
    }

    public String getPrenom(){
        return this.prenom.get();
    }

    public void setPrenom(String prenom){
        this.prenom.set(prenom);
    }

    public String toString(){
        return this.genre.get()+" "+this.prenom.get()+" "+this.nom.get();
    }

}
