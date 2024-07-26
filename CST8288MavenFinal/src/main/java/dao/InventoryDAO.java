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
    boolean addItem(InventoryDTO item);
    boolean updateItem(InventoryDTO item);
    InventoryDTO getItemById(int itemID);
    List<InventoryDTO> getAllItems();
}