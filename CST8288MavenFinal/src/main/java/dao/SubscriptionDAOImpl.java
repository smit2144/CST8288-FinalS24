
package dao;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

import model.SubscriptionDTO;

/**
 * Implementation of the {@link SubscriptionDAO} interface for interacting with subscription data in a database.
 * This class provides concrete implementations for adding, retrieving, and deleting subscriptions.
 * 
 * @author RS
 */
public class SubscriptionDAOImpl implements SubscriptionDAO {

    private Connection connection;
    
    /**
     * Constructs a {@code SubscriptionDAOImpl} with a database connection.
     * 
     * @param connection the database connection
     */
    public SubscriptionDAOImpl(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Retrieves all subscriptions from the database.
     * 
     * @return a list of all subscriptions
     */
    @Override
    public List<SubscriptionDTO> getAllSubscriptions() {
        List<SubscriptionDTO> subscriptions = new ArrayList<>();
        String query = "SELECT * FROM subscriptions"; // Ensure the table name matches your database schema
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
             
            while (rs.next()) {
                SubscriptionDTO subscription = new SubscriptionDTO();
                // Assuming SubscriptionDTO has a constructor accepting these parameters
                subscription.setSubscriptionID(rs.getInt("subscriptionId"));
                subscription.setUserID(rs.getInt("userId"));
                subscription.setLocation(rs.getString("location"));
                subscription.setFoodPreferences(rs.getString("foodPreferences"));
                subscription.setAlertMethod(rs.getString("alertMethod"));
                subscriptions.add(subscription);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log exception in real applications
        }
        return subscriptions;
    }
    
    /**
     * Adds a new subscription to the database.
     * 
     * @param subscription the subscription details
     * @return {@code true} if the operation was successful
     */
    @Override
    public boolean addSubscription(SubscriptionDTO subscription) {
        String sql = "INSERT INTO Subscriptions (UserID, Location, FoodPreferences, AlertMethod) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, subscription.getUserID());
            preparedStatement.setString(2, subscription.getLocation());
            preparedStatement.setString(3, subscription.getFoodPreferences());
            preparedStatement.setString(4, subscription.getAlertMethod());
            
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a subscription from the database based on its ID.
     * 
     * @param subscriptionId the ID of the subscription to delete
     * @return {@code true} if the operation was successful
     */
    @Override
    public boolean deleteSubscription(int subscriptionId) {
        String sql = "DELETE FROM Subscriptions WHERE SubscriptionID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, subscriptionId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Retrieves a list of subscriptions for a specific user by their user ID.
     * 
     * @param userId the ID of the user
     * @return a list of subscriptions for the specified user
     */
    @Override
    public List<SubscriptionDTO> getSubscriptionsByUserId(int userId) {
        List<SubscriptionDTO> subscriptions = new ArrayList<>();
        String sql = "SELECT * FROM subscriptions WHERE UserId = ?"; 
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    SubscriptionDTO subscription = new SubscriptionDTO();
                    subscription.setSubscriptionID(resultSet.getInt("SubscriptionId"));
                    subscription.setUserID(resultSet.getInt("UserId"));
                    subscription.setLocation(resultSet.getString("Location"));
                    subscription.setFoodPreferences(resultSet.getString("FoodPreferences"));
                    subscription.setAlertMethod(resultSet.getString("AlertMethod"));
                    subscriptions.add(subscription);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return subscriptions;
    }
    
    /**
     * Retrieves a list of subscriptions filtered by user type, location, and food group.
     * 
     * @param userType the user type (e.g., "charitable organization" or "consumer")
     * @param location the location
     * @param foodGroup the food group
     * @return a list of subscriptions matching the criteria
     */
    @Override
    public List<SubscriptionDTO> getSubscriptionsByTypeLocationAndFoodGroup(String userType, String location, String foodGroup) {
        List<SubscriptionDTO> matchedSubscriptions = new ArrayList<>();
        String sql = "SELECT * FROM Subscriptions WHERE UserType = ? AND Location = ? AND FoodPreferences = ?";
    
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userType);
            preparedStatement.setString(2, location);
            preparedStatement.setString(3, foodGroup);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    matchedSubscriptions.add(new SubscriptionDTO(
                        resultSet.getInt("SubscriptionID"),
                        resultSet.getInt("UserID"),
                        resultSet.getString("Location"),
                        resultSet.getString("FoodPreferences"),
                        resultSet.getString("AlertMethod")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return matchedSubscriptions;
    }
}