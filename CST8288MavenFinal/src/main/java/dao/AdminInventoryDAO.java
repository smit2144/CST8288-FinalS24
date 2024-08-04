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
    List<InventoryDTO> getAllInventory();
    
    /**
     * Adds a new inventory item.
     * @param inventory an instance of InventoryDTO object representing new inventory item to be added.
     */
    void addInventory(InventoryDTO inventory);
    
    /**
     * Update inventory item.
     * @param inventory, updated inventory item.
     */
    void updateInventory(InventoryDTO inventory);
    
    /**
     * Delete an inventory item.
     *
     * @param itemId, ID of the item to be deleted.
     * @return boolean indicating whether the deletion was successful.
     */
    boolean deleteInventory(int itemId);
    
    /**
     * Retrieves an inventory item by ID.
     * @param itemId, ID of the inventory item.
     * @return InventoryDTO object representing the inventory item.
     */
    InventoryDTO getInventoryById(int itemId);
    
    /**
     * Retrieve surplus inventory items.
     *
     * @return A list of InventoryDTO objects having surplus inventory items.
     */
    List<InventoryDTO> getSurplus();
    
    /**
     * Retrieve all inventory items with expiry date within a week.
     *
     * @return A list of InventoryDTO objects.
     * @throws SQLException, in case SQL exception occurs during retrieval process.
     */
    List<InventoryDTO> getExpiringItemsWithinOneWeek() throws SQLException;
    
    /**
     * Retrieve all users.
     *
     * @return A list of UserDTO objects.
     * @throws SQLException, in case SQL exception occurs during retrieval process.
     */
    List<UserDTO> getAllUsers() throws SQLException;
    
    /**
     * Retrieve surplus inventory items.
     *
     * @return A list of InventoryDTO objects having surplus inventory items.
     */
    List<InventoryDTO> getSurplusInventory();
}
