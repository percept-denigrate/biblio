import java.sql.*;
import java.io.*;

public class Init{
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/Biblio";
        String username = "root";
        String password = "JeHaisMySQL";
  
        int batchSize = 20;
 
        Connection connection = null;
        try {
 
            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);
 
            String sql = "";
            PreparedStatement statement = connection.prepareStatement(sql);
 
            int count = 0;
 
            lineReader.readLine(); // skip header line
 
            lineReader.close();
 
            // execute the remaining queries
            statement.executeBatch();
 
            connection.commit();
            connection.close();
 
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
 
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
	}
    }
}
 
