package dao;

import java.sql.SQLException;
import java.util.List;

import model.FoodItem;

public interface InventoryDAO {
    void addFoodItem(FoodItem foodItem) throws SQLException;
    List<FoodItem> getAllFoodItems() throws SQLException;
}
