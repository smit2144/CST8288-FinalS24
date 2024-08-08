package model;

public class Subscription {
    private int subscriptionId;
    private int userId;
    private String location;
    private String foodPreferences;
    private String alertMethod;

    public Subscription() {}

    public Subscription(int userId, String location, String foodPreferences, String alertMethod) {
        this.userId = userId;
        this.location = location;
        this.foodPreferences = foodPreferences;
        this.alertMethod = alertMethod;
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
