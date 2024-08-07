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
 */
public class AdminInventoryDAOImpl implements AdminInventoryDAO {
    
    @Override
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
                inventory.setLocation(resultSet.getString("Location"));
                inventory.setFoodGroup(resultSet.getString("FoodGroup"));
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

    @Override
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
            preparedStatement.setDate(4, new java.sql.Date(inventory.getExpirationDate().getTime()));
            preparedStatement.setString(5, inventory.getSurplusStatus());
            preparedStatement.setString(6, inventory.getPlan());
            preparedStatement.setDouble(7, inventory.getPrice());
            preparedStatement.setString(8, inventory.getLocation());
            preparedStatement.setString(9, inventory.getFoodGroup());
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

    @Override
    public InventoryDTO getInventoryById(int itemId) {
        InventoryDTO item = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

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
                item.setLocation(resultSet.getString("Location"));
                item.setFoodGroup(resultSet.getString("FoodGroup"));
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

    @Override
    public void updateInventory(InventoryDTO inventory) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "UPDATE fooditems SET Name=?, Quantity=?, ExpirationDate=?, SurplusStatus=?, Plan=?, Price=?, Location=?, FoodGroup=? WHERE ItemId=?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, inventory.getName());
            preparedStatement.setInt(2, inventory.getQuantity());
            preparedStatement.setDate(3, new java.sql.Date(inventory.getExpirationDate().getTime()));
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

    @Override
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

    @Override
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
                inventory.setLocation(resultSet.getString("Location"));
                inventory.setFoodGroup(resultSet.getString("FoodGroup"));
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

    @Override
    public List<InventoryDTO> getExpiringItemsWithinOneWeek() throws SQLException {
        List<InventoryDTO> expiringItems = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT * FROM fooditems WHERE ExpirationDate BETWEEN ? AND ?";
            preparedStatement = connection.prepareStatement(sql);

            LocalDate today = LocalDate.now();
            LocalDate oneWeekLater = today.plusWeeks(1);

            preparedStatement.setDate(1, Date.valueOf(today));
            preparedStatement.setDate(2, Date.valueOf(oneWeekLater));

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                InventoryDTO item = new InventoryDTO();
                item.setItemID(resultSet.getInt("ItemId"));
                item.setUserID(resultSet.getInt("UserId"));
                item.setName(resultSet.getString("Name"));
                item.setQuantity(resultSet.getInt("Quantity"));
                item.setExpirationDate(resultSet.getDate("ExpirationDate"));
                item.setSurplusStatus(resultSet.getString("SurplusStatus"));
                item.setPlan(resultSet.getString("Plan"));
                item.setPrice(resultSet.getDouble("Price"));
                item.setLocation(resultSet.getString("Location"));
                item.setFoodGroup(resultSet.getString("FoodGroup"));

                expiringItems.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
    public List<UserDTO> getAllUsers() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<UserDTO> users = new ArrayList<>();

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT * FROM users";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt("UserId");
                String name = resultSet.getString("Name");
                String email = resultSet.getString("Email");
                String password = resultSet.getString("Password");
                String userType = resultSet.getString("UserType");

                UserDTO user = new UserDTO(userId, name, email, password, userType);
                users.add(user);
            }
        } finally {
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

        return users;
    }

    @Override
    public void claimFoodItem(int itemId, int orgId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "UPDATE fooditems SET UserId = ?, Quantity = 0 WHERE ItemId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, orgId);
            preparedStatement.setInt(2, itemId);
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void purchaseFoodItem(int itemId, int userId, int quantity) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "UPDATE fooditems SET Quantity = Quantity - ? WHERE ItemId = ? AND Quantity >= ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, itemId);
            preparedStatement.setInt(3, quantity);
            int rowsUpdated = preparedStatement.executeUpdate();
            
            if (rowsUpdated == 0) {
                throw new SQLException("Insufficient quantity available.");
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
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
	public boolean deleteItem(int itemID) {
		// TODO Auto-generated method stub
		return false;
	}
}
