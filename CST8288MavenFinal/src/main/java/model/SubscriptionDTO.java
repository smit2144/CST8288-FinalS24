package model;

/**
 * Data Transfer Object (DTO) for Subscription.
 * This class represents the subscription details and is used to transfer subscription data between different layers of the application.
 * 
 */
public class SubscriptionDTO {
    private int subscriptionID;
    private int userID;
    private String location;
    private String foodPreferences;
    private String alertMethod;

    // Default constructor
    public SubscriptionDTO() {
    }

    // Constructor with parameters
    public SubscriptionDTO(int subscriptionID, int userID, String location, String foodPreferences, String alertMethod) {
        this.subscriptionID = subscriptionID;
        this.userID = userID;
        this.location = location;
        this.foodPreferences = foodPreferences;
        this.alertMethod = alertMethod;
    }

    // Getters and Setters
    public int getSubscriptionID() {
        return subscriptionID;
    }

    public void setSubscriptionID(int subscriptionID) {
        this.subscriptionID = subscriptionID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFoodPreferences() {
        return foodPreferences;
    }

    public void setFoodPreferences(String foodPreferences) {
        this.foodPreferences = foodPreferences;
    }

    public String getAlertMethod() {
        return alertMethod;
    }

    public void setAlertMethod(String alertMethod) {
        this.alertMethod = alertMethod;
    }
}
