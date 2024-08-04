package dao;

import util.DBConnection;
import model.InventoryDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of RetailerInventoryDAO interface for managing retailer inventory.
 * Provides methods to perform CRUD operations on retailer inventory data in the database.
 */
public class RetailerInventoryDAOImpl implements RetailerInventoryDAO {

    @Override
    public List<InventoryDTO> getAllInventory(int userId) {
        List<InventoryDTO> inventoryList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM fooditems WHERE UserId = ?")) {

            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    InventoryDTO inventory = new InventoryDTO();
                    inventory.setItemID(resultSet.getInt("ItemId"));
                    inventory.setUserID(resultSet.getInt("UserId"));
                    inventory.setName(resultSet.getString("Name"));
                    inventory.setQuantity(resultSet.getInt("Quantity"));
                    inventory.setExpirationDate(resultSet.getDate("ExpirationDate"));
                    inventory.setSurplusStatus(resultSet.getString("SurplusStatus"));
                    inventory.setPlan(resultSet.getString("Plan"));
                    inventory.setPrice(resultSet.getDouble("Price"));
                    inventory.setLocation(resultSet.getString("Location"));
                    inventory.setFoodGroup(resultSet.getString("FoodGroup"));
                    inventoryList.add(inventory);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle the exception as needed
        }
        return inventoryList;
    }

    @Override
    public void addInventory(InventoryDTO inventory) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO fooditems (UserId, Name, Quantity, ExpirationDate, SurplusStatus, Plan, Price, Location, FoodGroup) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setInt(1, inventory.getUserID());
            preparedStatement.setString(2, inventory.getName());
            preparedStatement.setInt(3, inventory.getQuantity());
            preparedStatement.setDate(4, new java.sql.Date(inventory.getExpirationDate().getTime())); // Convert util.Date to sql.Date
            preparedStatement.setString(5, inventory.getSurplusStatus());
            preparedStatement.setString(6, inventory.getPlan());
            preparedStatement.setDouble(7, inventory.getPrice());
            preparedStatement.setString(8, inventory.getLocation());
            preparedStatement.setString(9, inventory.getFoodGroup());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle the exception as needed
        }
    }

    @Override
    public InventoryDTO getInventoryById(int itemId) {
        InventoryDTO item = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM fooditems WHERE ItemId = ?")) {

            preparedStatement.setInt(1, itemId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    item = new InventoryDTO();
                    item.setItemID(resultSet.getInt("ItemId"));
                    item.setUserID(resultSet.getInt("UserId"));
                    item.setName(resultSet.getString("Name"));
                    item.setQuantity(resultSet.getInt("Quantity"));
                    item.setExpirationDate(resultSet.getDate("ExpirationDate"));
                    item.setSurplusStatus(resultSet.getString("SurplusStatus"));
                    item.setPlan(resultSet.getString("Plan"));
                    item.setPrice(resultSet.getDouble("Price"));
                    item.setLocation(resultSet.getString("Location"));
                    item.setFoodGroup(resultSet.getString("FoodGroup"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public void updateInventory(InventoryDTO inventory) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE fooditems SET Name=?, Quantity=?, ExpirationDate=?, SurplusStatus=?, Plan=?, Price=?, Location=?, FoodGroup=? WHERE ItemId=?")) {

            preparedStatement.setString(1, inventory.getName());
            preparedStatement.setInt(2, inventory.getQuantity());
            preparedStatement.setDate(3, new java.sql.Date(inventory.getExpirationDate().getTime())); // Convert util.Date to sql.Date
            preparedStatement.setString(4, inventory.getSurplusStatus());
            preparedStatement.setString(5, inventory.getPlan());
            preparedStatement.setDouble(6, inventory.getPrice());
            preparedStatement.setString(7, inventory.getLocation());
            preparedStatement.setString(8, inventory.getFoodGroup());
            preparedStatement.setInt(9, inventory.getItemID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteInventory(int itemId) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM fooditems WHERE ItemId = ?")) {

            preparedStatement.setInt(1, itemId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Return true if at least one row was deleted
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<InventoryDTO> getSurplus(int userId) {
        List<InventoryDTO> surplusItems = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM fooditems WHERE SurplusStatus = 'yes' AND UserId = ?")) {

            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    InventoryDTO inventory = new InventoryDTO();
                    inventory.setItemID(resultSet.getInt("ItemId"));
                    inventory.setUserID(resultSet.getInt("UserId"));
                    inventory.setName(resultSet.getString("Name"));
                    inventory.setQuantity(resultSet.getInt("Quantity"));
                    inventory.setExpirationDate(resultSet.getDate("ExpirationDate"));
                    inventory.setSurplusStatus(resultSet.getString("SurplusStatus"));
                    inventory.setPlan(resultSet.getString("Plan"));
                    inventory.setPrice(resultSet.getDouble("Price"));
                    inventory.setLocation(resultSet.getString("Location"));
                    inventory.setFoodGroup(resultSet.getString("FoodGroup"));
                    surplusItems.add(inventory);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return surplusItems;
    }

    @Override
    public List<InventoryDTO> getExpiringItemsWithinOneWeek(int userId) throws SQLException {
        List<InventoryDTO> expiringItems = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM fooditems WHERE ExpirationDate BETWEEN ? AND ? AND UserId = ?")) {

            // Calculate dates for one week from today
            LocalDate today = LocalDate.now();
            LocalDate oneWeekLater = today.plusWeeks(1);

            // Set the start and end dates for the query
            preparedStatement.setDate(1, java.sql.Date.valueOf(today));
            preparedStatement.setDate(2, java.sql.Date.valueOf(oneWeekLater));
            preparedStatement.setInt(3, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    InventoryDTO item = new InventoryDTO();
                    item.setItemID(resultSet.getInt("ItemId"));
                    item.setUserID(resultSet.getInt("UserId"));
                    item.setName(resultSet.getString("Name"));
                    item.setQuantity(resultSet.getInt("Quantity"));
                    item.setExpirationDate(resultSet.getDate("ExpirationDate"));
                    item.setSurplusStatus(resultSet.getString("SurplusStatus"));
                    item.setPlan(resultSet.getString("Plan"));
                    item.setPrice(resultSet.getDouble("Price"));
                    item.setLocation(resultSet.getString("Location"));
                    item.setFoodGroup(resultSet.getString("FoodGroup"));
                    expiringItems.add(item);
                }
            }
        }
        return expiringItems;
    }

    // Unused methods, could be removed or implemented based on additional requirements

    @Override
    public boolean addItem(InventoryDTO item) {
        // Not implemented
        return false;
    }

    @Override
    public boolean updateItem(InventoryDTO item) {
        // Not implemented
        return false;
    }

    @Override
    public InventoryDTO getItemById(int itemID) {
        // Not implemented
        return null;
    }

    @Override
    public List<InventoryDTO> getAllItems() {
        // Not implemented
        return null;
    }

    @Override
    public List<InventoryDTO> getSurplusItems() {
        // Not implemented
        return null;
    }
}
