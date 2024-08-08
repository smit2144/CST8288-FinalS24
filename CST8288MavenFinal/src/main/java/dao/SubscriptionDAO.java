package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.DBConnection;
import model.Subscription;

public class SubscriptionDAO {

    public void addSubscription(Subscription subscription) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "INSERT INTO Subscriptions (UserID, Location, FoodPreferences, AlertMethod) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, subscription.getUserId());
                statement.setString(2, subscription.getLocation());
                statement.setString(3, subscription.getFoodPreferences());
                statement.setString(4, subscription.getAlertMethod());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSubscription(int subscriptionId) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "DELETE FROM Subscriptions WHERE SubscriptionID = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, subscriptionId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
