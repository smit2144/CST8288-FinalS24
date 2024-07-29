
package dao;

import java.sql.SQLException;
import java.util.List;
import model.InventoryDTO;


public interface RetailerInventoryDAO extends InventoryDAO{
    /**
     * Retrieves all inventory items associated with a specific retailer.
     * @param userId The ID of the retailer whose inventory items are to be retrieved.
     * @return A list of InventoryDTO objects representing all inventory items associated with the retailer.
     */
    public List<InventoryDTO> getAllInventory(int userId);
    /**
     * Adds a new inventory item for the retailer.
     * @param inventory The InventoryDTO object representing the inventory item to be added.
     */
    public void addInventory(InventoryDTO inventory);
    /**
     * Retrieve an inventory item by its ID.
     * @param itemId of the inventory item to retrieve.
     * @return The InventoryDTO object representing the retrieved inventory item.
     */
    public InventoryDTO getInventoryById(int itemId);
    /**
     * Updates an existing inventory item.
     * @param inventory The InventoryDTO object representing the updated inventory item.
     */
    public void updateInventory(InventoryDTO inventory);
    /**
     * Deletes an inventory item by its ID.
     * @param itemId The ID of the inventory item to delete.
     */
    public void deleteInventory(int itemId);
    /**
     * Retrieves surplus inventory items associated with a specific retailer.
     * @param userId The ID of the retailer whose surplus inventory items are to be retrieved.
     * @return A list of InventoryDTO objects representing surplus inventory items associated with the retailer.
     */
    public List<InventoryDTO> getSurplus(int userId);
    /**
     * Retrieves inventory items expiring within one week associated with a specific retailer.
     * @param userId The ID of the retailer whose expiring inventory items are to be retrieved.
     * @return A list of InventoryDTO objects representing expiring inventory items associated with the retailer.
     * @throws SQLException If an SQL exception occurs during the retrieval process.
     */
    public List<InventoryDTO> getExpiringItemsWithinOneWeek(int userId) throws SQLException;
}
