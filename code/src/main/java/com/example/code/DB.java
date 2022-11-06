package com.example.code;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    public static Connection con(){
        try {
            String jdbcURL = "jdbc:mysql://localhost:3306/Biblio";
            String username = "root";
            String password = "JeHaisMySQL";
            Connection con = null;
            con = DriverManager.getConnection(jdbcURL, username, password);
            return con;
        }catch(Exception e){ System.err.println(e); return null;}
    }
}
