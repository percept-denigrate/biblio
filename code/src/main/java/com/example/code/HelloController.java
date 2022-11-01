package com.example.code;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Vector;

public class HelloController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private int id;
    private int n = 0;
    private Boolean listeRouge;

    @FXML
    private TextField emailField;
    @FXML
    private String email;
    @FXML
    private Text loginText;

    @FXML
    private Text texteListeRouge;
    @FXML
    private Text empruntNombre;
    @FXML
    private Button boutonEmprunter;
    @FXML
    private Text empruntDisplay;
    @FXML
    private TextField empruntISBN;
    @FXML
    private Text rendreLivres;

    @FXML
    private TableColumn<Usager, String> categorieColonne;
    @FXML
    private TableColumn<Usager, String> emailColonne;
    @FXML
    private TableColumn<Usager, String> listeRougeColonne;
    @FXML
    private TableColumn<Usager, String> nomColonne;
    @FXML
    private TableColumn<Usager, String> prenomColonne;
    @FXML
    private TableView<Usager> usagersTable;

    @FXML
    public void connecter(ActionEvent event) throws IOException {
        email = emailField.getText();
        try {
            Connection con = DB.connecter();
            String sql = "SELECT COUNT(*) as C FROM Usager WHERE email=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            int c = 0;
            while(rs.next()){
                c = rs.getInt("C");
            }
            if(c==0){
                loginText.setText("Cette adresse n'est pas enregistrée.");
            }else{
                sql = "SELECT Usager.id as id,categorie FROM Usager WHERE email=?";
                PreparedStatement stmt2 = con.prepareStatement(sql);
                stmt2.setString(1, email);
                ResultSet rs2 = stmt2.executeQuery();
                String categorie = "";
                while (rs2.next()){
                    categorie = rs2.getString("categorie");
                    id = rs2.getInt("id");
                }
                String appli = "appli.fxml"; //A changer en "appliBasique.fxml";
                if(categorie.equals("admin")){
                    appli = "appli.fxml";
                    System.out.println("admin");
                }
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(appli));
                loader.setController(this);
                Parent root = (Parent) loader.load();
                listeRouge = estListeRouge();
                if(listeRouge) {
                    texteListeRouge.setText("Vous ne pouvez pas emprunter car vous avez été placé sur liste rouge.");
                    empruntNombre.setText("");
                    rendreLivres.setText("");
                } else
                    afficherNombreEmprunts();
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                afficherUsagers();
            }
            con.close();
        } catch(Exception e){ System.err.println(e);}

    }

    @FXML
    public void afficherNombreEmprunts()throws IOException{
        try{
            Connection con = DB.connecter();
            String sql = "SELECT COUNT(*) as c FROM Usager JOIN Emprunt ON Usager.id=Emprunt.usager WHERE Emprunt.fin IS NULL;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int emprunts = 0;
            while(rs.next()){
                emprunts = rs.getInt("C");
            }
            sql = "SELECT Categorie.nb_max as max FROM Categorie JOIN Usager ON Categorie.nom=Usager.categorie WHERE Usager.id=?;";
            PreparedStatement stmt2 = con.prepareStatement(sql);
            stmt2.setString(1,""+id);
            ResultSet rs2 = stmt2.executeQuery();
            int max = 0;
            while (rs2.next()){
                max = rs2.getInt("max");
            }
            con.close();
            n = max - emprunts;
        }catch (Exception e){ System.err.println(e);}
        empruntNombre.setText("Vous pouvez emprunter "+n+" livre(s).");
    }

    private Boolean estListeRouge() {
        try {
            Connection con = DB.connecter();
            String sql = "SELECT COUNT(*) as c FROM Usager JOIN Liste_rouge ON Usager.id=Liste_rouge.usager WHERE Usager.id=? AND Liste_rouge.fin IS NULL;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            int c = 0;
            while (rs.next()) {
                c = rs.getInt("c");
            }
            con.close();
            return c >= 1;
        }catch (Exception e){ System.err.println(e); return false;}
    }

    @FXML
    public void emprunter(ActionEvent event){
        if(n==0) return;
        if(listeRouge) return;
        try {
            String ISBN = empruntISBN.getText();
            Connection con = DB.connecter();
            String sql = "SELECT Livre.id as id FROM Livre WHERE ISBN=?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, ISBN);
            ResultSet rs = stmt.executeQuery();
            Vector<Integer> idListe = new Vector<Integer>();
            while (rs.next()) {
                idListe.add(rs.getInt("id"));
            }
            if(idListe.size()==0){
                empruntDisplay.setText("Cette édition n'est pas disponible dans la bibliothèque.");
            }else{
                int idDispo = -1;
                for(int i = 0; i<idListe.size(); i++){
                    sql = "SELECT COUNT(*) as c FROM Livre JOIN Emprunt ON Livre.id=Emprunt.livre WHERE id=? AND fin IS NULL;";
                    PreparedStatement stmt2 = con.prepareStatement(sql);
                    stmt2.setInt(1, idListe.get(i));
                    ResultSet rs2 = stmt2.executeQuery();
                    rs2.next();
                    if(rs2.getInt("c") == 0){
                        idDispo = idListe.get(i);
                        break;
                    }
                }
                if(idDispo == -1)
                    empruntDisplay.setText("Tous les livres de cette édition sont déjà empruntés.");
                else{
                    empruntDisplay.setText("Emprunt validé");
                    sql = "INSERT INTO Emprunt VALUES(?,?,?,NULL);";
                    PreparedStatement stmt3 = con.prepareStatement(sql);
                    stmt3.setInt(1,idDispo);
                    stmt3.setInt(2,id);
                    stmt3.setDate(3, Date.valueOf(LocalDate.now()));
                    stmt3.executeUpdate();
                    System.out.println("Emprunt validé");
                }
            }
            con.close();
        }catch(Exception e){ System.err.println(e);}
    }

    @FXML
    public void afficherUsagers(){
        prenomColonne.setCellValueFactory(new PropertyValueFactory<Usager, String>("prenom"));
        nomColonne.setCellValueFactory(new PropertyValueFactory<Usager, String>("nom"));
        emailColonne.setCellValueFactory(new PropertyValueFactory<Usager, String>("email"));
        categorieColonne.setCellValueFactory(new PropertyValueFactory<Usager, String>("categorie"));
        try{
            Connection con = DB.connecter();
            String sql = "SELECT * FROM Usager;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                usagersTable.getItems().add(new Usager(rs.getString("prenom"),rs.getString("nom"),rs.getString("email"),rs.getString("categorie")));
            }
            con.close();
        }catch(Exception e){ System.err.println(e);}
    }
}