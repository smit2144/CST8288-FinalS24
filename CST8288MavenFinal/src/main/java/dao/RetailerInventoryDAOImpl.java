package dao;

import model.InventoryDTO;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of InventoryDAO for managing inventory for retailers.
 * Provides methods for adding, updating, retrieving, and listing inventory items.
 */
public class RetailerInventoryDAOImpl implements InventoryDAO {
    private static final String INSERT_ITEM_SQL = "INSERT INTO inventory (userID, name, quantity, expirationDate, surplusStatus, plan, price, location, foodGroup) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_ITEM_SQL = "UPDATE inventory SET name = ?, quantity = ?, expirationDate = ?, surplusStatus = ?, plan = ?, price = ?, location = ?, foodGroup = ? WHERE itemID = ?";
    private static final String SELECT_ITEM_BY_ID = "SELECT * FROM inventory WHERE itemID = ?";
    private static final String SELECT_ALL_ITEMS = "SELECT * FROM inventory";
    private static final String SELECT_SURPLUS_ITEMS = "SELECT * FROM inventory WHERE expirationDate <= ? AND surplusStatus = 'Excess'";

    /**
     * Adds a new item to the inventory.
     * 
     * @param item The InventoryDTO object representing the item to be added.
     * @return true if the item was successfully added, false otherwise.
     */
    @Override
    public boolean addItem(InventoryDTO item) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ITEM_SQL)) {
            preparedStatement.setInt(1, item.getUserID());
            preparedStatement.setString(2, item.getName());
            preparedStatement.setInt(3, item.getQuantity());
            preparedStatement.setDate(4, new java.sql.Date(item.getExpirationDate().getTime()));
            preparedStatement.setString(5, item.getSurplusStatus());
            preparedStatement.setString(6, item.getPlan());
            preparedStatement.setDouble(7, item.getPrice());
            preparedStatement.setString(8, item.getLocation());
            preparedStatement.setString(9, item.getFoodGroup());
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates an existing item in the inventory.
     * 
     * @param item The InventoryDTO object representing the item to be updated.
     * @return true if the item was successfully updated, false otherwise.
     */
    @Override
    public boolean updateItem(InventoryDTO item) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ITEM_SQL)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setInt(2, item.getQuantity());
            preparedStatement.setDate(3, new java.sql.Date(item.getExpirationDate().getTime()));
            preparedStatement.setString(4, item.getSurplusStatus());
            preparedStatement.setString(5, item.getPlan());
            preparedStatement.setDouble(6, item.getPrice());
            preparedStatement.setString(7, item.getLocation());
            preparedStatement.setString(8, item.getFoodGroup());
            preparedStatement.setInt(9, item.getItemID());
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves an item by its ID.
     * 
     * @param itemID The ID of the item to be retrieved.
     * @return The InventoryDTO object representing the item with the specified ID, or null if not found.
     */
    @Override
    public InventoryDTO getItemById(int itemID) {
        InventoryDTO item = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ITEM_BY_ID)) {
            preparedStatement.setInt(1, itemID);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                item = new InventoryDTO();
                item.setItemID(rs.getInt("itemID"));
                item.setUserID(rs.getInt("userID"));
                item.setName(rs.getString("name"));
                item.setQuantity(rs.getInt("quantity"));
                item.setExpirationDate(rs.getDate("expirationDate"));
                item.setSurplusStatus(rs.getString("surplusStatus"));
                item.setPlan(rs.getString("plan"));
                item.setPrice(rs.getDouble("price"));
                item.setLocation(rs.getString("location"));
                item.setFoodGroup(rs.getString("foodGroup"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * Retrieves all items in the inventory.
     * 
     * @return A list of InventoryDTO objects representing all items.
     */
    @Override
    public List<InventoryDTO> getAllItems() {
        List<InventoryDTO> items = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ITEMS);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                InventoryDTO item = new InventoryDTO();
                item.setItemID(rs.getInt("itemID"));
                item.setUserID(rs.getInt("userID"));
                item.setName(rs.getString("name"));
                item.setQuantity(rs.getInt("quantity"));
                item.setExpirationDate(rs.getDate("expirationDate"));
                item.setSurplusStatus(rs.getString("surplusStatus"));
                item.setPlan(rs.getString("plan"));
                item.setPrice(rs.getDouble("price"));
                item.setLocation(rs.getString("location"));
                item.setFoodGroup(rs.getString("foodGroup"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    /**
     * Retrieves surplus items from the inventory.
     * Surplus items are those that are nearing expiration within one week and flagged as excess.
     * 
     * @return A list of InventoryDTO objects representing surplus items.
     */
    @Override
    public List<InventoryDTO> getSurplusItems() {
        List<InventoryDTO> items = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SURPLUS_ITEMS)) {
            // Set the date to one week from today
            java.sql.Date oneWeekFromToday = new java.sql.Date(System.currentTimeMillis() + 7L * 24 * 60 * 60 * 1000);
            preparedStatement.setDate(1, oneWeekFromToday);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                InventoryDTO item = new InventoryDTO();
                item.setItemID(rs.getInt("itemID"));
                item.setUserID(rs.getInt("userID"));
                item.setName(rs.getString("name"));
                item.setQuantity(rs.getInt("quantity"));
                item.setExpirationDate(rs.getDate("expirationDate"));
                item.setSurplusStatus(rs.getString("surplusStatus"));
                item.setPlan(rs.getString("plan"));
                item.setPrice(rs.getDouble("price"));
                item.setLocation(rs.getString("location"));
                item.setFoodGroup(rs.getString("foodGroup"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
}
