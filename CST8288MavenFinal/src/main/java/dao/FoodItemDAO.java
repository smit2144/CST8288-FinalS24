package dao;

import model.FoodItem;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodItemDAO {
    
    public void addFoodItem(FoodItem foodItem) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "INSERT INTO FoodItems (UserID, Name, Quantity, ExpirationDate, SurplusStatus, Plan, Price, Location, FoodGroup) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, foodItem.getUserID());
            statement.setString(2, foodItem.getName());
            statement.setInt(3, foodItem.getQuantity());
            statement.setString(4, foodItem.getExpirationDate());
            statement.setString(5, foodItem.getSurplusStatus());
            statement.setString(6, foodItem.getPlan());
            statement.setDouble(7, foodItem.getPrice());
            statement.setString(8, foodItem.getLocation());
            statement.setString(9, foodItem.getFoodGroup());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFoodItem(FoodItem foodItem) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "UPDATE FoodItems SET UserID = ?, Name = ?, Quantity = ?, ExpirationDate = ?, SurplusStatus = ?, Plan = ?, Price = ?, Location = ?, FoodGroup = ? WHERE ItemID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, foodItem.getUserID());
            statement.setString(2, foodItem.getName());
            statement.setInt(3, foodItem.getQuantity());
            statement.setString(4, foodItem.getExpirationDate());
            statement.setString(5, foodItem.getSurplusStatus());
            statement.setString(6, foodItem.getPlan());
            statement.setDouble(7, foodItem.getPrice());
            statement.setString(8, foodItem.getLocation());
            statement.setString(9, foodItem.getFoodGroup());
            statement.setInt(10, foodItem.getItemID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void claimFoodItem(int itemID, int userID) throws SQLException {
        String query = "UPDATE FoodItems SET UserID = ? WHERE ItemID = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, userID);
            statement.setInt(2, itemID);

            statement.executeUpdate();
        }
    }

    public void purchaseFoodItem(int itemID, int userID) throws SQLException {
        String query = "UPDATE FoodItems SET UserID = ?, Plan = 'keep' WHERE ItemID = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, userID);
            statement.setInt(2, itemID);

            statement.executeUpdate();
        }
    }

    public List<FoodItem> getFoodItemsByUser(int userId) {
        List<FoodItem> foodItems = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT * FROM FoodItems WHERE UserID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                FoodItem foodItem = new FoodItem();
                foodItem.setItemID(resultSet.getInt("ItemID"));
                foodItem.setUserID(resultSet.getInt("UserID"));
                foodItem.setName(resultSet.getString("Name"));
                foodItem.setQuantity(resultSet.getInt("Quantity"));
                foodItem.setExpirationDate(resultSet.getString("ExpirationDate"));
                foodItem.setSurplusStatus(resultSet.getString("SurplusStatus"));
                foodItem.setPlan(resultSet.getString("Plan"));
                foodItem.setPrice(resultSet.getDouble("Price"));
                foodItem.setLocation(resultSet.getString("Location"));
                foodItem.setFoodGroup(resultSet.getString("FoodGroup"));
                foodItems.add(foodItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foodItems;
    }
    public FoodItem getFoodItemById(int itemID) throws SQLException {
        FoodItem foodItem = null;
        String query = "SELECT * FROM FoodItems WHERE ItemID = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, itemID);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    foodItem = new FoodItem();
                    foodItem.setItemID(resultSet.getInt("ItemID"));
                    foodItem.setUserID(resultSet.getInt("UserID"));
                    foodItem.setName(resultSet.getString("Name"));
                    foodItem.setQuantity(resultSet.getInt("Quantity"));
                    foodItem.setExpirationDate(resultSet.getString("ExpirationDate"));
                    foodItem.setSurplusStatus(resultSet.getString("SurplusStatus"));
                    foodItem.setPlan(resultSet.getString("Plan"));
                    foodItem.setPrice(resultSet.getDouble("Price"));
                    foodItem.setLocation(resultSet.getString("Location"));
                    foodItem.setFoodGroup(resultSet.getString("FoodGroup"));
                }
            }
        }
        return foodItem;
    }
}
