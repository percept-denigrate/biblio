package com.example.code;

public class Usager {

    private String nom;
    private String prenom;
    private String email;
    private String categorie;
    private String listeRouge;
    private int id;

    public Usager(String prenom, String nom, String email, String categorie, String listeRouge, int id) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.categorie = categorie;
        this.listeRouge = listeRouge;
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getListeRouge() {
        return listeRouge;
    }

    public void setListeRouge(String listeRouge) {
        this.listeRouge = listeRouge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
