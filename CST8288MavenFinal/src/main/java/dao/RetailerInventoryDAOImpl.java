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
 * 
 */
public class RetailerInventoryDAOImpl implements RetailerInventoryDAO {

    /**
     * Retrieves all inventory items associated with a specific retailer.
     * @param userId The ID of the retailer whose inventory items are to be retrieved.
     * @return A list of InventoryDTO objects representing all inventory items associated with the retailer.
     */
    public List<InventoryDTO> getAllInventory(int userId) {
        List<InventoryDTO> inventoryList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT * FROM fooditems WHERE UserId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

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
        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle the exception as needed
        } finally {
            // Close resources in reverse order of their creation to avoid resource leaks
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return inventoryList;
    }



    /**
     * Adds a new inventory item for the retailer.
     * 
     * @param inventory The InventoryDTO object representing the inventory item to be added.
     */
    public void addInventory(InventoryDTO inventory) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "INSERT INTO fooditems (UserId, Name, Quantity, ExpirationDate, SurplusStatus, Plan, Price, Location, FoodGroup) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
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
        } finally {
            // Close resources in reverse order of their creation to avoid resource leaks
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Retrieves an inventory item by its ID.
     * @param itemId The ID of the inventory item to retrieve.
     * @return The InventoryDTO object representing the retrieved inventory item.
     */
    public InventoryDTO getInventoryById(int itemId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        InventoryDTO item = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT * FROM FoodItems WHERE ItemID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, itemId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                item = new InventoryDTO();
                item.setItemID(resultSet.getInt("ItemID"));
                item.setUserID(resultSet.getInt("UserID"));
                item.setName(resultSet.getString("Name"));
                item.setQuantity(resultSet.getInt("Quantity"));
                item.setExpirationDate(resultSet.getDate("ExpirationDate"));
                item.setSurplusStatus(resultSet.getString("SurplusStatus"));
                item.setPlan(resultSet.getString("Plan"));
                item.setPrice(resultSet.getDouble("Price"));
                item.setLocation(resultSet.getString("Location")); // Add location
                item.setFoodGroup(resultSet.getString("FoodGroup")); // Add food group
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

        return item;
    }


    /**
     * Updates an existing inventory item.
     * @param inventory The InventoryDTO object representing the updated inventory item.
     */
    public void updateInventory(InventoryDTO inventory) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "UPDATE fooditems SET Name=?, Quantity=?, ExpirationDate=?, SurplusStatus=?, Plan=?, Price=?, Location=?, FoodGroup=? WHERE ItemId=?";
            preparedStatement = connection.prepareStatement(sql);

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
        } finally {
            try {
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
    }



    /**
     * Deletes an inventory item by its ID.
     * @param itemId The ID of the inventory item to delete.
     */
    public void deleteInventory(int itemId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "DELETE FROM fooditems WHERE ItemId=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, itemId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
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
    }

    /**
     * Retrieves surplus inventory items associated with a specific retailer.
     * @param userId The ID of the retailer whose surplus inventory items are to be retrieved.
     * @return A list of InventoryDTO objects representing surplus inventory items associated with the retailer.
     */
    public List<InventoryDTO> getSurplus(int userId) {
        List<InventoryDTO> surplusItems = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT * FROM fooditems WHERE SurplusStatus = 'yes' AND UserId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

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
                inventory.setLocation(resultSet.getString("Location")); // Add location
                inventory.setFoodGroup(resultSet.getString("FoodGroup")); // Add food group
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
     * Retrieves inventory items expiring within one week associated with a specific retailer.
     * @param userId The ID of the retailer whose expiring inventory items are to be retrieved.
     * @return A list of InventoryDTO objects representing expiring inventory items associated with the retailer.
     * @throws SQLException If an SQL exception occurs during the retrieval process.
     */
    public List<InventoryDTO> getExpiringItemsWithinOneWeek(int userId) throws SQLException {
        List<InventoryDTO> expiringItems = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT * FROM fooditems WHERE ExpirationDate BETWEEN ? AND ? AND UserId = ?";
            preparedStatement = connection.prepareStatement(sql);

            // Calculate dates for one week from today
            LocalDate today = LocalDate.now();
            LocalDate oneWeekLater = today.plusWeeks(1);

            // Set the start and end dates for the query
            preparedStatement.setDate(1, java.sql.Date.valueOf(today));
            preparedStatement.setDate(2, java.sql.Date.valueOf(oneWeekLater));
            preparedStatement.setInt(3, userId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Populate item details from result set
                InventoryDTO item = new InventoryDTO();
                item.setItemID(resultSet.getInt("ItemId"));
                item.setUserID(resultSet.getInt("UserId"));
                item.setName(resultSet.getString("Name"));
                item.setQuantity(resultSet.getInt("Quantity"));
                item.setExpirationDate(resultSet.getDate("ExpirationDate"));
                item.setSurplusStatus(resultSet.getString("SurplusStatus"));
                item.setPlan(resultSet.getString("Plan"));
                item.setPrice(resultSet.getDouble("Price"));
                item.setLocation(resultSet.getString("Location")); // Add location
                item.setFoodGroup(resultSet.getString("FoodGroup")); // Add food group

                expiringItems.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return expiringItems;
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
	public InventoryDTO getItemById(int itemID) {
		// TODO Auto-generated method stub
		return null;
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
}