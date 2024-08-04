package dao;

import model.InventoryDTO;
import java.util.List;


/**
 * interface to access and manage inventory data
 * This interface serves as a contract for classes implementing this interface
 * to provide inventory management functionality.
 * 
 * @author Hussein
 */
public interface InventoryDAO {
	
	/**
     * Adds a new item to the inventory.
     * 
     * @param item The InventoryDTO object representing the item to be added.
     * @return true if the item was successfully added, false otherwise.
     */
    boolean addItem(InventoryDTO item);
    
    /**
     * Updates an existing item in the inventory.
     * 
     * @param item The InventoryDTO object representing the item to be updated.
     * @return true if the item was successfully updated, false otherwise.
     */
    boolean updateItem(InventoryDTO item);
    
    /**
     * Retrieves an item by its ID.
     * 
     * @param itemID The ID of the item to be retrieved.
     * @return The InventoryDTO object representing the item with the specified ID, or null if not found.
     */
    InventoryDTO getItemById(int itemID);
    
    /**
     * Retrieves all items in the inventory.
     * 
     * @return A list of InventoryDTO objects representing all items.
     */
    List<InventoryDTO> getAllItems();
    
    /**
     * Retrieves surplus items from the inventory.
     * 
     * @return A list of InventoryDTO objects representing surplus items.
     */
    List<InventoryDTO> getSurplusItems(); // New method for surplus items
    
    /**
     * Deletes an item from the inventory.
     * 
     * @param itemID The ID of the item to be deleted.
     * @return true if the item was successfully deleted, false otherwise.
     */
    boolean deleteItem(int itemID); // New method for deleting items
}