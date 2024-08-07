package dao;

import java.sql.*;
import java.sql.Connection;
import util.DBConnection;
import model.User;

public class UserDAOImpl implements UserDAO{
	
	public static Connection dbConn = null;
	
	public UserDAOImpl() {
		try { 
			dbConn = DBConnection.getInstance().getConnection();
		} catch (SQLException e) {
			System.out.println("Unable to establish connection" + e.getMessage());
		}
	}
	
	/**
	 * This method insert data to the users table in the database.
	 * @throws SQLException
	 */
	@Override
	public void insertUser(User user) throws SQLException {
		String insertUser = "INSERT INTO Users (username, password, email, phone, user_type, isSubscribed) VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = dbConn.prepareStatement(insertUser)) {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassWord());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getPhone());
            preparedStatement.setString(5, user.getUserType());
            preparedStatement.setBoolean(6, user.getIsSubscribed());
            preparedStatement.executeUpdate();
        }
		catch( SQLException ex) {
			System.out.println(ex.getMessage());
		}

    }
	
	/**
	 * This method validates user's data for login authentication.
	 * @throws SQLException
	 */
	@Override
	public User validateUser(String email, String password) throws SQLException {
        String query = "SELECT user_id, username, password, email, phone, user_type FROM Users WHERE email = ? AND password = ?";
        try (PreparedStatement preparedStatement = dbConn.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("user_id")); // Use 'user_id' instead of 'id'
                user.setUserName(rs.getString("username"));
                user.setPassWord(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getInt("phone"));
                user.setUserType(rs.getString("user_type"));
                return user;
            }
        }
        return null;
    }
	
	
	/**
	 * This method counts user's login.
	 * This is part of the notification feature.
	 * @throws SQLException
	 */
	@Override
	public int getUserCount(String username) throws SQLException {
        String sql = "SELECT count FROM Users WHERE username = ?";
        try (PreparedStatement ps = dbConn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("count");
                } else {
                    return 0; // or handle the case where the user does not exist
                }
            }
        }
    }
	
	/**
	 * This method reset the count of user's login.
	 * This is part of the notification feature.
	 * @throws SQLException
	 */
	@Override
	public void resetUserCount(String username) throws SQLException {
	    String updateCount = "UPDATE Users SET count = 0 WHERE username = ?";
	    try (PreparedStatement preparedStatement = dbConn.prepareStatement(updateCount)) {
	        preparedStatement.setString(1, username);
	        preparedStatement.executeUpdate();
	    }
	}
}