package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/FoodWasteReductionDB";
    private static final String USER = "root";
    private static final String PASSWORD = "yourpassword";

    private static Connection connection = null;

    private DBConnection() {}

    public static Connection getConnection() throws SQLException {
        try {
            if (connection == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
            }
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Failed to load database driver");
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
