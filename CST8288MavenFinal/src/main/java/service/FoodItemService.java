package service;

import dao.FoodItemDAO;
import model.FoodItem;

import java.sql.SQLException;
import java.util.List;

public class FoodItemService {
    private FoodItemDAO foodItemDAO = new FoodItemDAO();

    public void claimFoodItem(int itemId) throws SQLException {
        foodItemDAO.claimFoodItem(itemId, itemId);
    }

    public void purchaseFoodItem(int itemId) throws SQLException {
        foodItemDAO.purchaseFoodItem(itemId, itemId);
    }

    public List<FoodItem> getFoodItemsByUser(int userId) {
        return foodItemDAO.getFoodItemsByUser(userId);
    }

    public void addFoodItem(FoodItem foodItem) {
        foodItemDAO.addFoodItem(foodItem);
    }

    public void updateFoodItem(FoodItem foodItem) {
        foodItemDAO.updateFoodItem(foodItem);
    }
}
