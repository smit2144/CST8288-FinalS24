package dao;

import java.sql.SQLException;
import java.util.List;
import model.InventoryDTO;
import model.UserDTO;

/**
 * @author Hussein
 */

public interface AdminInventoryDAO extends InventoryDAO {
    /**
     * Retrieves all inventory items.
     * @return list of InventoryDTO objects representing inventory items.
     */
    public List<InventoryDTO> getAllInventory();
    
    /**
     * Adds a new inventory item.
     * @param inventory an instance of InventoryDTO object representing new inventory inventory item to be added.
     */
    public void addInventory(InventoryDTO inventory);
    
    /**
     * Update inventory item.
     * @param inventory , updated inventory item.
     */
    public void updateInventory(InventoryDTO inventory);
    
    /**
     * Delete an inventory item.
     * @param itemId, ID of the deleted item.
     */
    public void deleteInventory(int itemId);
    
    /**
     * Retrieve Surplus Inventory Items.
     * @return A list of InventoryDTO objects having surplus inventory items.
     */
    public List<InventoryDTO> getSurplus();
    
    /**
     * Retrieve all inventory items with expiry-date within a week.
     * @return A list of InventoryDTO objects.
     * @throws SQLException, in case SQL exception occurs during retrieval process.
     */
    public List<InventoryDTO> getExpiringItemsWithinOneWeek() throws SQLException;
    
    /**
     * Retrieve all users.
     * @return A list of UserDTO objects.
     * @throws SQLException, in case SQL exception occurs during retrieval process.
     */
    public List<UserDTO> getAllUsers() throws SQLException;

    /**
     * Claim a food item by a charitable organization.
     * @param itemId, ID of the item to be claimed.
     * @param orgId, ID of the organization claiming the item.
     * @throws SQLException, in case SQL exception occurs during the update process.
     */
    public void claimFoodItem(int itemId, int orgId) throws SQLException;

    /**
     * Purchase a food item by a consumer.
     * @param itemId, ID of the item to be purchased.
     * @param userId, ID of the user purchasing the item.
     * @param quantity, Quantity of the item to be purchased.
     * @throws SQLException, in case SQL exception occurs during the update process.
     */
    public void purchaseFoodItem(int itemId, int userId, int quantity) throws SQLException;

    // Add this method to the interface
    /**
     * Retrieve an inventory item by its ID.
     * @param itemId, ID of the inventory item.
     * @return InventoryDTO representing the inventory item.
     * @throws SQLException, in case SQL exception occurs during retrieval process.
     */
    public InventoryDTO getInventoryById(int itemId) throws SQLException;
}
