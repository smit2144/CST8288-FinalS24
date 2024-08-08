package service;

import java.util.Date;
import java.util.regex.Pattern;
import model.*;

public class AppValidator {
	// Validate user input in UserDTO
    public static boolean isValidUser(UserDTO user) {
        return isValidName(user) &&
               isValidEmail(user) &&
               isValidPassword(user) &&
               isValidUserType(user);
    }

    // Validate name
    public static boolean isValidName(UserDTO user) {
        String userName = user.getName();
        return !userName.trim().isEmpty();
    }

    // Validate email
    public static boolean isValidEmail(UserDTO user) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        String email = user.getEmail();
        return email.matches(emailRegex);
    }

    // Validate password
    public static boolean isValidPassword(UserDTO user) {
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        String password = user.getPassword();
        return password.matches(passwordRegex);
    }

    // Validate user type
    private static boolean isValidUserType(UserDTO user) {
        String userType = user.getUserType();
        return userType.equals("retailer") || userType.equals("consumer") || userType.equals("charitable organization");
    }
    
    public static boolean isValidInventory(InventoryDTO inventory) {
        return isValidName(inventory.getName()) &&
               isValidQuantity(inventory.getQuantity()) &&
               isValidExpirationDate(inventory.getExpirationDate()) &&
               isValidSurplusStatus(inventory.getSurplusStatus()) &&
               isValidPlan(inventory.getPlan()) &&
               isValidPrice(inventory.getPrice());
    }

    public static boolean isValidName(String name) {
        // Add validation rules for name here (e.g., length, allowed characters, etc.)
        return name != null && !name.isEmpty();
    }

    public static boolean isValidQuantity(int quantity) {
        // Add validation rules for quantity here (e.g., range, positive value, etc.)
        return quantity >= 0;
    }

    public static boolean isValidExpirationDate(Date expirationDate) {
        // Add validation rules for expiration date here (e.g., not in the past, etc.)
        return expirationDate != null && expirationDate.after(new Date());
    }

    public static boolean isValidSurplusStatus(String surplusStatus) {
    // Add validation rules for surplus status here (e.g., valid values, etc.)
    return surplusStatus != null && (surplusStatus.equals("yes") || surplusStatus.equals("no"));
    
    }
    public static boolean isValidPlan(String plan) {
    // Add validation rules for plan here (e.g., length, allowed values, etc.)
    return plan != null && !plan.isEmpty();
}

    public static boolean isValidPrice(double price) {
    // Add validation rules for price here (e.g., range, positive value, etc.)
    return price >= 0; // Assuming price cannot be negative
    }

}
