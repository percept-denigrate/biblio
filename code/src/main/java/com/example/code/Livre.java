package com.example.code;

public class Livre {
    private String titre;
    private String auteur;
    private int date;
    private String edition;
    private int ISBN;
    private String emprunteur;

    public Livre(String titre, String auteur, int date, String edition, int ISBN) {
        this.titre = titre;
        this.auteur = auteur;
        this.date = date;
        this.edition = edition;
        this.ISBN = ISBN;
    }

    public Livre(String titre, String auteur, int date, String edition, int ISBN, String emprunteur) {
        this.titre = titre;
        this.auteur = auteur;
        this.date = date;
        this.edition = edition;
        this.ISBN = ISBN;
        this.emprunteur = emprunteur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getEmprunteur() {
        return emprunteur;
    }

    public void setEmprunteur(String emprunteur) {
        this.emprunteur = emprunteur;
    }
}
