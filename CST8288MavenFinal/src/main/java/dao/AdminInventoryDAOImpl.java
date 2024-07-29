package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.InventoryDTO;
import model.UserDTO;
import util.DBConnection;

/**
 * Implementation of the AdminInventoryDAO interface providing methods to manage inventory items and users.
 * This class interacts with the database to perform CRUD operations on inventory items and retrieve user data.
 * 
 * @author Hussein
 */
public class AdminInventoryDAOImpl implements AdminInventoryDAO {
    /**
     * Retrieves all inventory items from the database.
     * 
     * @return A list of InventoryDTO objects representing all inventory items.
     */
    public List<InventoryDTO> getAllInventory() {
        List<InventoryDTO> inventoryList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT * FROM fooditems";
            preparedStatement = connection.prepareStatement(sql);
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
                inventory.setLocation(resultSet.getString("Location")); // Added location field
                inventory.setFoodGroup(resultSet.getString("FoodGroup")); // Added food group field
                inventoryList.add(inventory);
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

        return inventoryList;
    }

    /**
     * Adds a new inventory item to the database.
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
            preparedStatement.setString(8, inventory.getLocation()); // Added location field
            preparedStatement.setString(9, inventory.getFoodGroup()); // Added food group field
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
     * Retrieves an inventory item by its ID from the database.
     * 
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
                item.setLocation(resultSet.getString("Location")); // Include location field
                item.setFoodGroup(resultSet.getString("FoodGroup")); // Include food group field
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
     * Updates an existing inventory item in the database.
     * 
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
            preparedStatement.setDate(3, (Date) inventory.getExpirationDate());
            preparedStatement.setString(4, inventory.getSurplusStatus());
            preparedStatement.setString(5, inventory.getPlan());
            preparedStatement.setDouble(6, inventory.getPrice());
            preparedStatement.setString(7, inventory.getLocation()); // Include location field
            preparedStatement.setString(8, inventory.getFoodGroup()); // Include food group field
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
     * Deletes an inventory item from the database by its ID.
     * 
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
     * Retrieves all surplus inventory items from the database.
     * 
     * @return A list of InventoryDTO objects representing surplus inventory items.
     */
    public List<InventoryDTO> getSurplus() {
        List<InventoryDTO> surplusItems = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT * FROM fooditems WHERE SurplusStatus = 'yes'";
            preparedStatement = connection.prepareStatement(sql);
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
                inventory.setLocation(resultSet.getString("Location")); // Include location field
                inventory.setFoodGroup(resultSet.getString("FoodGroup")); // Include food group field
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
     * Retrieves all users from the database.
     * 
     * @return A list of UserDTO objects representing all users.
     */
    public List<UserDTO> getAllUsers() {
        List<UserDTO> userList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT * FROM users";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                UserDTO user = new UserDTO();
                user.setUserId(resultSet.getInt("UserId"));
                user.setName(resultSet.getString("Name"));
                user.setEmail(resultSet.getString("Email"));
                user.setPassword(resultSet.getString("Password"));
                user.setUserType(resultSet.getString("UserType")); // UserType corresponds to user role
                userList.add(user);
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

        return userList;
    }

    /**
     * Retrieves a user by their ID from the database.
     * 
     * @param userId The ID of the user to retrieve.
     * @return The UserDTO object representing the retrieved user.
     */
    public UserDTO getUserById(int userId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        UserDTO user = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT * FROM users WHERE UserId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new UserDTO();
                user.setUserId(resultSet.getInt("UserId"));
                user.setName(resultSet.getString("Name"));
                user.setEmail(resultSet.getString("Email"));
                user.setPassword(resultSet.getString("Password"));
                user.setUserType(resultSet.getString("UserType")); // UserType corresponds to user role
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

        return user;
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

	@Override
	public List<InventoryDTO> getExpiringItemsWithinOneWeek() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InventoryDTO> getSurplusInventory() {
		// TODO Auto-generated method stub
		return null;
	}
}

