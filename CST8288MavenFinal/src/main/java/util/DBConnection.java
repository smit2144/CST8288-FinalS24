package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
   
	private static DBConnection dbConnect;
    private static Connection conn;
    
    private DBConnection() throws SQLException {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/FoodWasteReductionDB";
            String user = "root";
            String pass = "Wartown_221";
            
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static synchronized DBConnection getInstance() throws SQLException {
        DBConnection dbConn = DBConnection.dbConnect;
        if (dbConn == null) {
            synchronized (DBConnection.class) {
                dbConn = DBConnection.dbConnect;
                if (dbConn == null) {
                    DBConnection.dbConnect = dbConn = new DBConnection();
                }
            }
        }
        return dbConn;
    }     
    
    public Connection getConnection() {
        return conn;
    }
}