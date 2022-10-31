package com.example.code;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class HelloController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField emailField;
    @FXML
    private String email;
    @FXML
    private Text loginText;
    @FXML
    public void connecter(ActionEvent event) throws IOException {
        email = emailField.getText();

        try {
            String jdbcURL = "jdbc:mysql://localhost:3306/Biblio";
            String username = "root";
            String password = "JeHaisMySQL";
            Connection con = null;
            con = DriverManager.getConnection(jdbcURL, username, password);
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
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("appli.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } catch(Exception e){ System.out.println(e);}
    }
}