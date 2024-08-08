package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.FoodItem;
import util.DBConnection;

public class InventoryDAOImpl implements InventoryDAO {
    @Override
    public void addFoodItem(FoodItem foodItem) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "INSERT INTO FoodItems (name, quantity, expirationDate, surplusStatus, plan, price, location, foodGroup, userID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, foodItem.getName());
        statement.setInt(2, foodItem.getQuantity());
        statement.setString(3, foodItem.getExpirationDate());
        statement.setString(4, foodItem.getSurplusStatus());
        statement.setString(5, foodItem.getPlan());
        statement.setDouble(6, foodItem.getPrice());
        statement.setString(7, foodItem.getLocation());
        statement.setString(8, foodItem.getFoodGroup());
        statement.setInt(9, foodItem.getUserID());
        statement.executeUpdate();
        connection.close();
    }

    @Override
    public List<FoodItem> getAllFoodItems() throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT * FROM FoodItems";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        List<FoodItem> foodItems = new ArrayList<>();
        while (resultSet.next()) {
            FoodItem foodItem = new FoodItem();
            foodItem.setItemID(resultSet.getInt("ItemID"));
            foodItem.setUserID(resultSet.getInt("userID"));
            foodItem.setName(resultSet.getString("name"));
            foodItem.setQuantity(resultSet.getInt("quantity"));
            foodItem.setExpirationDate(resultSet.getString("expirationDate"));
            foodItem.setSurplusStatus(resultSet.getString("surplusStatus"));
            foodItem.setPlan(resultSet.getString("plan"));
            foodItem.setPrice(resultSet.getDouble("price"));
            foodItem.setLocation(resultSet.getString("location"));
            foodItem.setFoodGroup(resultSet.getString("foodGroup"));
            foodItems.add(foodItem);
        }

        connection.close();
        return foodItems;
    }

    @Override
    public void updateFoodItem(FoodItem foodItem) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "UPDATE FoodItems SET name = ?, quantity = ?, expirationDate = ?, surplusStatus = ?, plan = ?, price = ?, location = ?, foodGroup = ?, userID = ? WHERE ItemID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, foodItem.getName());
        statement.setInt(2, foodItem.getQuantity());
        statement.setString(3, foodItem.getExpirationDate());
        statement.setString(4, foodItem.getSurplusStatus());
        statement.setString(5, foodItem.getPlan());
        statement.setDouble(6, foodItem.getPrice());
        statement.setString(7, foodItem.getLocation());
        statement.setString(8, foodItem.getFoodGroup());
        statement.setInt(9, foodItem.getUserID());
        statement.setInt(10, foodItem.getItemID());
        statement.executeUpdate();
        connection.close();
    }

    @Override
    public void claimFoodItem(int itemID, int userID) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "UPDATE FoodItems SET userID = ? WHERE ItemID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, userID);
        statement.setInt(2, itemID);
        statement.executeUpdate();
        connection.close();
    }

    @Override
    public void purchaseFoodItem(int itemID, int userID) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "UPDATE FoodItems SET userID = ?, plan = 'keep' WHERE ItemID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, userID);
        statement.setInt(2, itemID);
        statement.executeUpdate();
        connection.close();
    }

    @Override
    public List<FoodItem> getFoodItemsByUser(int userId) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT * FROM FoodItems WHERE userID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();

        List<FoodItem> foodItems = new ArrayList<>();
        while (resultSet.next()) {
            FoodItem foodItem = new FoodItem();
            foodItem.setItemID(resultSet.getInt("ItemID"));
            foodItem.setUserID(resultSet.getInt("userID"));
            foodItem.setName(resultSet.getString("name"));
            foodItem.setQuantity(resultSet.getInt("quantity"));
            foodItem.setExpirationDate(resultSet.getString("expirationDate"));
            foodItem.setSurplusStatus(resultSet.getString("surplusStatus"));
            foodItem.setPlan(resultSet.getString("plan"));
            foodItem.setPrice(resultSet.getDouble("price"));
            foodItem.setLocation(resultSet.getString("location"));
            foodItem.setFoodGroup(resultSet.getString("foodGroup"));
            foodItems.add(foodItem);
        }

        connection.close();
        return foodItems;
    }

    @Override
    public FoodItem getFoodItemById(int itemID) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT * FROM FoodItems WHERE ItemID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, itemID);
        ResultSet resultSet = statement.executeQuery();

        FoodItem foodItem = null;
        if (resultSet.next()) {
            foodItem = new FoodItem();
            foodItem.setItemID(resultSet.getInt("ItemID"));
            foodItem.setUserID(resultSet.getInt("userID"));
            foodItem.setName(resultSet.getString("name"));
            foodItem.setQuantity(resultSet.getInt("quantity"));
            foodItem.setExpirationDate(resultSet.getString("expirationDate"));
            foodItem.setSurplusStatus(resultSet.getString("surplusStatus"));
            foodItem.setPlan(resultSet.getString("plan"));
            foodItem.setPrice(resultSet.getDouble("price"));
            foodItem.setLocation(resultSet.getString("location"));
            foodItem.setFoodGroup(resultSet.getString("foodGroup"));
        }

        connection.close();
        return foodItem;
    }
}
