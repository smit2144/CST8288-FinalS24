package service;

import dao.FoodItemDAO;
import model.FoodItem;

import java.sql.SQLException;
import java.util.List;

public class FoodItemService {
    private FoodItemDAO foodItemDAO = new FoodItemDAO();

    public void claimFoodItem(int itemId, int userId) throws SQLException {
        foodItemDAO.claimFoodItem(itemId, userId);
    }

    public void purchaseFoodItem(int itemId, int userId) throws SQLException {
        foodItemDAO.purchaseFoodItem(itemId, userId);
    }

    public List<FoodItem> getFoodItemsByUser(int userId) {
        return foodItemDAO.getFoodItemsByUser(userId);
    }

    public List<FoodItem> getAllFoodItems() {
        return foodItemDAO.getAllFoodItems();
    }

    public void addFoodItem(FoodItem foodItem) {
        foodItemDAO.addFoodItem(foodItem);
    }

    public void updateFoodItem(FoodItem foodItem) {
        foodItemDAO.updateFoodItem(foodItem);
    }
}
