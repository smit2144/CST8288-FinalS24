package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.UserDTO;
import util.DBConnection;

public class RegistrationDAOImpl implements RegistrationDAO {
    private static final String INSERT_USER_SQL = "INSERT INTO Users (Name, Email, Password, UserType) VALUES (?, ?, ?, ?)";
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM Users WHERE Email = ?";

    @Override
    public boolean addUser(UserDTO user) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getUserType());
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        UserDTO user = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = new UserDTO();
                user.setName(rs.getString("Name"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setUserType(rs.getString("UserType"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
