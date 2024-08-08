package service;

import java.sql.SQLException;
import java.util.List;

import dao.InventoryDAO;
import dao.InventoryDAOImpl;
import model.FoodItem;

public class InventoryService {
    private InventoryDAO inventoryDAO = new InventoryDAOImpl();

    public void addFoodItem(FoodItem foodItem) throws SQLException {
        inventoryDAO.addFoodItem(foodItem);
    }

    public List<FoodItem> getAllFoodItems() throws SQLException {
        return inventoryDAO.getAllFoodItems();
    }
}
