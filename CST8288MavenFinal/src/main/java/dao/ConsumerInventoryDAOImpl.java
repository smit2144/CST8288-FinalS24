package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBConnection;
import model.InventoryDTO;

/**
 * Implementation of the InventoryDAO interface customized for consumer inventory management.
 * This class provides methods to retrieve surplus items intended for sale from the database.
 * 
 * @author Hussein
 */

public class ConsumerInventoryDAOImpl implements InventoryDAO {
	
	  /**
     * Retrieves surplus inventory items marked for sale from the database.
     * 
     * @return A list of InventoryDTO objects representing surplus inventory items for sale.
     */
    public List<InventoryDTO> getSurplus() {
        List<InventoryDTO> surplusItems = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT * FROM fooditems WHERE SurplusStatus = 'yes' AND Plan = 'sale'";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                InventoryDTO inventory = new InventoryDTO();
                inventory.setItemID(resultSet.getInt("ItemId"));
                inventory.setName(resultSet.getString("Name"));
                inventory.setQuantity(resultSet.getInt("Quantity"));
                inventory.setExpirationDate(resultSet.getDate("ExpirationDate"));
                inventory.setSurplusStatus(resultSet.getString("SurplusStatus"));
                inventory.setPlan(resultSet.getString("Plan"));
                inventory.setPrice(resultSet.getDouble("Price"));
                surplusItems.add(inventory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return surplusItems;
    }
    /**
     * Retrieves an inventory item by its ID from the database.
     * 
     * @param itemId of the inventory item to be retrieved.
     * @return The InventoryDTO object representing the retrieved item.
     */
    public InventoryDTO getItemById(int itemId) {
        InventoryDTO inventory = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT * FROM fooditems WHERE ItemId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, itemId);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                inventory = new InventoryDTO();
                inventory.setItemID(resultSet.getInt("ItemId"));
                inventory.setName(resultSet.getString("Name"));
                inventory.setQuantity(resultSet.getInt("Quantity"));
                inventory.setExpirationDate(resultSet.getDate("ExpirationDate"));
                inventory.setSurplusStatus(resultSet.getString("SurplusStatus"));
                inventory.setPlan(resultSet.getString("Plan"));
                inventory.setPrice(resultSet.getDouble("Price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return inventory;
    }
	@Override
	public boolean addItem(InventoryDTO item) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean updateItem(InventoryDTO item) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<InventoryDTO> getAllItems() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<InventoryDTO> getSurplusItems() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean deleteItem(int itemID) {
		// TODO Auto-generated method stub
		return false;
	}

}