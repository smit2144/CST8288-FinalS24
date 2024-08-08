package service;

import dao.SubscriptionDAO;
import model.Subscription;

public class SubscriptionService {
    private SubscriptionDAO subscriptionDAO = new SubscriptionDAO();

    public void addSubscription(int userId, String location, String foodPreferences, String alertMethod) {
        Subscription subscription = new Subscription(userId, location, foodPreferences, alertMethod);
        subscriptionDAO.addSubscription(subscription);
    }

    public void deleteSubscription(int subscriptionId) {
        subscriptionDAO.deleteSubscription(subscriptionId);
    }
}
