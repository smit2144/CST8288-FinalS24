<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Inventory</title>
    <link rel="stylesheet" href="styles.css"> <!-- Link to your CSS file -->
    <script defer src="scripts.js"></script> <!-- Link to your JavaScript file -->
</head>
<body>
    <header>
        <h1>Update Inventory</h1>
    </header>

    <main>
        <h2>Update Inventory Item</h2>
        <form action="process_update_inventory.jsp" method="post">
            <!-- Form fields to update inventory -->
            <label for="itemId">Item ID:</label>
            <input type="text" id="itemId" name="itemId" required><br>
            
            <label for="itemName">Item Name:</label>
            <input type="text" id="itemName" name="itemName"><br>
            
            <label for="itemQuantity">Quantity:</label>
            <input type="number" id="itemQuantity" name="itemQuantity"><br>
            
            <label for="itemExpiry">Expiry Date:</label>
            <input type="date" id="itemExpiry" name="itemExpiry"><br>

            <button type="submit">Update Item</button>
        </form>
        <p><a href="AdminDashboard.jsp">Back to Dashboard</a></p>
    </main>

    <footer>
        <p>&copy; 2024 Your Company. All rights reserved.</p>
    </footer>
</body>
</html>
