package dao;

import java.sql.SQLException;
import java.util.List;

import model.Subscription;
import model.SubscriptionDTO;

/**
 * Interface for Subscription Data Access Object.
 * This interface defines the methods that need to be implemented for managing subscriptions in the database.
 * 
 * @author RS
 */
public interface SubscriptionDAO {
    /**
     * Retrieves all subscriptions from the database.
     * 
     * @return a list of all subscriptions
     */
    List<SubscriptionDTO> getAllSubscriptions() throws SQLException;

    /**
     * Adds a new subscription to the database.
     * 
     * @param subscription the subscription details
     * @return {@code true} if the operation was successful
     */
    boolean addSubscription(Subscription subscription) throws SQLException;

    /**
     * Deletes a subscription from the database based on its ID.
     * 
     * @param subscriptionId the ID of the subscription to delete
     * @return {@code true} if the operation was successful
     */
    boolean deleteSubscription(int subscriptionId) throws SQLException;

    /**
     * Retrieves a list of subscriptions for a specific user by their user ID.
     * 
     * @param userId the ID of the user
     * @return a list of subscriptions for the specified user
     */
    List<SubscriptionDTO> getSubscriptionsByUserId(int userId) throws SQLException;

    /**
     * Retrieves a list of subscriptions filtered by user type, location, and food group.
     * 
     * @param userType the user type (e.g., "charitable organization" or "consumer")
     * @param location the location
     * @param foodGroup the food group
     * @return a list of subscriptions matching the criteria
     */
    List<SubscriptionDTO> getSubscriptionsByTypeLocationAndFoodGroup(String userType, String location, String foodGroup) throws SQLException;
}
