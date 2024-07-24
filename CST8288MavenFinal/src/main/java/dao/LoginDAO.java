package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.UserDTO;
import util.DBConnection;
/**
 * to authenticate users by verifying their email and password in database.
 * class LoginDAO interacts with database to check credentials
 * 
 * @author Hussein
 */
public class LoginDAO {
	
	/**
     * Retrieve a user from the database based on provided email and password.
     * 
     * @param email address of the user.
     * @param password of the user.
     * @return A UserDTO object for the authenticated user, or null if no user matches the provided credentials.
     */
    public UserDTO getUserByEmailAndPassword(String email, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        UserDTO user = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT * FROM Users WHERE Email = ? AND Password = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new UserDTO();
                user.setUserId(resultSet.getInt("UserID"));
                user.setName(resultSet.getString("Name"));
                user.setEmail(resultSet.getString("Email"));
                user.setPassword(resultSet.getString("Password"));
                user.setUserType(resultSet.getString("UserType"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

}
