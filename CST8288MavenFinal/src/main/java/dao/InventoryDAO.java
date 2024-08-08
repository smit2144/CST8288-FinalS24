package dao;

import model.FoodItem;

import java.sql.SQLException;
import java.util.List;

public interface InventoryDAO {
    void addFoodItem(FoodItem foodItem) throws SQLException;
    List<FoodItem> getAllFoodItems() throws SQLException;
    void updateFoodItem(FoodItem foodItem) throws SQLException;
    void claimFoodItem(int itemID, int userID) throws SQLException;
    void purchaseFoodItem(int itemID, int userID) throws SQLException;
    List<FoodItem> getFoodItemsByUser(int userId) throws SQLException;
    FoodItem getFoodItemById(int itemID) throws SQLException;
}
