package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.UserDTO;
import util.DBConnection;

public class RegistrationDAOImpl implements RegistrationDAO {

    @Override
    public boolean addUser(UserDTO user) {
        String sql = "INSERT INTO Users (Name, Email, Password, UserType) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getUserType());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        String sql = "SELECT * FROM Users WHERE Email = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    UserDTO user = new UserDTO();
                    user.setUserId(resultSet.getInt("UserID"));
                    user.setName(resultSet.getString("Name"));
                    user.setEmail(resultSet.getString("Email"));
                    user.setPassword(resultSet.getString("Password"));
                    user.setUserType(resultSet.getString("UserType"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
