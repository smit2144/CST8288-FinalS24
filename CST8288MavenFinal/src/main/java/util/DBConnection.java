package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class to manage database connection
 * Singleton Design pattern applied on this class 
 * 
 * @author Hussein
 */
public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/FoodWasteReductionDB";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    
    private static Connection connection = null;
    
    private DBConnection() {}
    
    public static Connection getConnection() throws SQLException{
    	 try {
             if (connection == null) { 
             // Load the MySQL JDBC driver
             Class.forName("com.mysql.cj.jdbc.Driver");
             }
             // Establish the database connection
             return DriverManager.getConnection(URL, USER, PASSWORD);
         } catch (ClassNotFoundException e) {
             // Handle ClassNotFoundException
             e.printStackTrace();
             throw new SQLException("Failed to load database driver");
         }
    }
    
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // Handle SQLException
                e.printStackTrace();
            }
        }
    }
}
