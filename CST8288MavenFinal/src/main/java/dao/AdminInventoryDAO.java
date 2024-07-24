package dao;

import java.sql.SQLDataException;
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
     * @param inventory an instance of InventoryDTO object representing new inventory inventory item to be added .
     */
    public void addInventory(InventoryDTO inventory);
    
    /**
     * Update inventory item
     * @param inventory , updated inventory item
     */
    public void updateInventory(InventoryDTO inventory);
    
    /**
     * Delete an inventory item.
     *
     * @param itemId, ID of the deleted item.
     */
    public void deleteInventory(int itemId);
    
    /**
     * Retrieve Surplus Inventory Items.
     *
     * @return A list of InventoryDTO objects having surplus inventory items.
     */
    public List<InventoryDTO> getSurplus();
    
    /**
     * Retrieve all inventory items with expiry-date within a week.
     *
     * @return A list of InventoryDTO objects.
     * @throws SQLException, in case SQL exception occurs during retrieval process.
     */
    public List<InventoryDTO> getExpiringItemsWithinOneWeek()throws SQLException;
    
    /**
     * Retrieve all users.
     *
     * @return A list of UserDTO objects.
     * @throws SQLException, in case SQL exception occurs during retrieval process.
     */
    public List<UserDTO> getAllUsers() throws SQLException;
    
}
