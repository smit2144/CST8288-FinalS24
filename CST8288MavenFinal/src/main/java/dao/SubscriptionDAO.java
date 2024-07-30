package dao;

import java.util.List;
import model.SubscriptionDTO;

public interface SubscriptionDAO {
	
    /**
     * Add a subscription.
     * @param Subscription details.
     * @return true if added successfully.
     */
	boolean addSubscription(SubscriptionDTO subscription);
    
    /**
     * Get subscriptions by user ID.
     * @param userID.
     * @return List of subscriptions.
     */
    List<SubscriptionDTO> getSubscriptionsByUserId(int userId);
    
    /**
     * Delete a subscription by ID.
     * @param subscriptionID.
     * @return true if deleted successfully.
     */
    boolean deleteSubscription(int subscriptionId);
    
    /**
     * Get all subscriptions.
     * @return List of all subscriptions.
     */
    List<SubscriptionDTO> getAllSubscriptions();
    
    /**
     * Get subscriptions by type, location, and foodGroup.
     * @param userType.
     * @param location.
     * @param foodGroup.
     * @return List of matching subscriptions.
     */
    List<SubscriptionDTO> getSubscriptionsByTypeLocationAndFoodGroup(String userType, String location, String foodGroup);

}
