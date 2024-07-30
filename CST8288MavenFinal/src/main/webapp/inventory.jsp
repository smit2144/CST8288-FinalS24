<%@ page import="java.util.List, model.InventoryDTO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inventory Management</title>
</head>
<body>
    <h1>Inventory Management</h1>

    <!-- Form for adding new inventory items -->
    <form action="manageInventory" method="post">
        <input type="hidden" name="action" value="add">
        <h2>Add New Item</h2>
        <label for="name">Item Name:</label>
        <input type="text" id="name" name="name" required><br>
        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" required><br>
        <label for="expirationDate">Expiration Date:</label>
        <input type="date" id="expirationDate" name="expirationDate" required><br>
        <label for="price">Price:</label>
        <input type="number" id="price" name="price" step="0.01" required><br>
        <label for="location">Location:</label>
        <input type="text" id="location" name="location" required><br>
        <label for="foodGroup">Food Group:</label>
        <input type="text" id="foodGroup" name="foodGroup" required><br>
        <label for="plan">Plan:</label>
        <input type="text" id="plan" name="plan"><br>
        <label for="surplusStatus">Surplus Status:</label>
        <select id="surplusStatus" name="surplusStatus">
            <option value="none">None</option>
            <option value="donation">Donation</option>
            <option value="sale">Sale</option>
        </select><br>
        <button type="submit">Add Item</button>
    </form>

    <!-- Form for updating existing inventory items -->
    <form action="manageInventory" method="post">
        <input type="hidden" name="action" value="update">
        <h2>Update Existing Item</h2>
        <label for="itemId">Item ID:</label>
        <input type="number" id="itemId" name="itemId" required><br>
        <label for="name">Item Name:</label>
        <input type="text" id="name" name="name"><br>
        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity"><br>
        <label for="expirationDate">Expiration Date:</label>
        <input type="date" id="expirationDate" name="expirationDate"><br>
        <label for="price">Price:</label>
        <input type="number" id="price" name="price" step="0.01"><br>
        <label for="location">Location:</label>
        <input type="text" id="location" name="location"><br>
        <label for="foodGroup">Food Group:</label>
        <input type="text" id="foodGroup" name="foodGroup"><br>
        <label for="plan">Plan:</label>
        <input type="text" id="plan" name="plan"><br>
        <label for="surplusStatus">Surplus Status:</label>
        <select id="surplusStatus" name="surplusStatus">
            <option value="none">None</option>
            <option value="donation">Donation</option>
            <option value="sale">Sale</option>
        </select><br>
        <button type="submit">Update Item</button>
    </form>
</body>
</html>
