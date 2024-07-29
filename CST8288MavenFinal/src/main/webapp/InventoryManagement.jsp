<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.InventoryDTO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inventory Management</title>
</head>
<body>
    <h2>Inventory Management</h2>
    
    <form action="ManageInventory" method="post">
        <h3>Add/Update Inventory Item</h3>
        <label for="itemID">Item ID:</label>
        <input type="number" id="itemID" name="itemID"><br><br>
        
        <label for="userID">User ID:</label>
        <input type="number" id="userID" name="userID" required><br><br>
        
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br><br>
        
        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" required><br><br>
        
        <label for="expirationDate">Expiration Date:</label>
        <input type="date" id="expirationDate" name="expirationDate" required><br><br>
        
        <label for="surplusStatus">Surplus Status:</label>
        <input type="text" id="surplusStatus" name="surplusStatus"><br><br>
        
        <label for="plan">Plan:</label>
        <input type="text" id="plan" name="plan"><br><br>
        
        <label for="price">Price:</label>
        <input type="number" id="price" name="price" step="0.01"><br><br>
        
        <label for="location">Location:</label>
        <input type="text" id="location" name="location"><br><br>
        
        <label for="foodGroup">Food Group:</label>
        <input type="text" id="foodGroup" name="foodGroup"><br><br>
        
        <input type="submit" value="Submit">
    </form>
</body>
</html>
