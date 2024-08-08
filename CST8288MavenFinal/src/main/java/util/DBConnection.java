package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for managing database connections using the Singleton pattern.
 */
public class DBConnection {
    // JDBC URL, username, and password for connecting to the database
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/FoodWasteReductionDB";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "yA262098";

    // Singleton instance, volatile to ensure visibility of changes across threads
    private static volatile DBConnection instance;
    private Connection connection;

    // Private constructor to prevent instantiation
    private DBConnection() {}

    // Method to obtain a database connection
    public static DBConnection getInstance() {
        if (instance == null) {
            synchronized (DBConnection.class) {
                if (instance == null) {
                    instance = new DBConnection();
                }
            }
        }
        return instance;
    }

    // Method to obtain a database connection
    public Connection getConnection() {
        // Establish the database connection
        Connection connection;
		try {
			connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		    System.out.println(connection);
	        return connection;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }

    // Method to close a database connection
    public void closeConnection(Connection connection) {
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
