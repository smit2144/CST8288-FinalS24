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
        String sql = "INSERT INTO FoodItem (name, quantity, expirationDate, surplusStatus, plan, price, location, foodGroup, userID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, foodItem.getName());
        statement.setInt(2, foodItem.getQuantity());
        statement.setString(3, foodItem.getExpirationDate());
        statement.setString(4, foodItem.getSurplusStatus());
        statement.setString(5, foodItem.getPlan());
        statement.setDouble(6, foodItem.getPrice());
        statement.setString(7, foodItem.getLocation());
        statement.setString(8, foodItem.getFoodGroup());
        statement.setInt(9, foodItem.getUserID()); // Set the userID
        statement.executeUpdate();
        connection.close();
    }

    @Override
    public List<FoodItem> getAllFoodItems() throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT * FROM FoodItem";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        List<FoodItem> foodItems = new ArrayList<>();
        while (resultSet.next()) {
            FoodItem foodItem = new FoodItem();
            foodItem.setUserID(resultSet.getInt("id"));
            foodItem.setName(resultSet.getString("name"));
            foodItem.setQuantity(resultSet.getInt("quantity"));
            foodItem.setExpirationDate(resultSet.getString("expirationDate"));
            foodItem.setSurplusStatus(resultSet.getString("surplusStatus"));
            foodItem.setPlan(resultSet.getString("plan"));
            foodItem.setPrice(resultSet.getDouble("price"));
            foodItem.setLocation(resultSet.getString("location"));
            foodItem.setFoodGroup(resultSet.getString("foodGroup"));
            foodItem.setUserID(resultSet.getInt("userID"));
            foodItems.add(foodItem);
        }

        connection.close();
        return foodItems;
    }
}
