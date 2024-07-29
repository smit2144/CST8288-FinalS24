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
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (Name, Email, Password, UserType) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getUserType());
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        UserDTO user = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE Email = ?")) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = new UserDTO();
                    user.setUserId(resultSet.getInt("UserId"));
                    user.setName(resultSet.getString("Name"));
                    user.setEmail(resultSet.getString("Email"));
                    user.setPassword(resultSet.getString("Password"));
                    user.setUserType(resultSet.getString("UserType"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
