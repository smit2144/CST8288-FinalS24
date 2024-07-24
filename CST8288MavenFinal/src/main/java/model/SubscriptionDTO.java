package model;


/**
 * @author Hussein
 */

public class SubscriptionDTO {
	private int subscriptionID;
	private int userID;
	private String location;
	private String foodPreferences;
	private String alertMethod;
	
	//Constructors
	
	public SubscriptionDTO() {
	
	}

	public SubscriptionDTO(int subscriptionID, int userID, String location, String foodPreferences,
			String alertMethod) {
		super();
		this.subscriptionID = subscriptionID;
		this.userID = userID;
		this.location = location;
		this.foodPreferences = foodPreferences;
		this.alertMethod = alertMethod;
	}

	
	//Getters and Setters
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

	
	//toString Method
	@Override
	public String toString() {
		return "SubscriptionDTO [subscriptionID=" + subscriptionID + ", userID=" + userID + ", location=" + location
				+ ", foodPreferences=" + foodPreferences + ", alertMethod=" + alertMethod + "]";
	}
	
	

}
