<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="styles.css"> <!-- Link to your CSS file -->
    <script defer src="scripts.js"></script> <!-- Link to your JavaScript file -->
</head>
<body>
    <header>
        <h1>Admin Dashboard</h1>
        <nav>
            <ul>
                <li><a href="AdminDashboard.jsp">Home</a></li>
                <li><a href="AdminAddInventory.jsp">Add Inventory</a></li>
                <li><a href="AdminExpiringItem.jsp">Expiring Items</a></li>
                <li><a href="admin_surplus.jsp">Surplus Items</a></li>
                <li><a href="admin_transactions.jsp">Transactions</a></li>
                <li><a href="AdminUpdate.jsp">Update Inventory</a></li>
                <li><a href="logout.jsp">Logout</a></li> <!-- Assuming there's a logout page -->
            </ul>
        </nav>
    </header>

    <main>
        <h2>Welcome to the Admin Dashboard</h2>
        <p>Select an option from the menu to manage the platform.</p>
    </main>

    <footer>
        <p>&copy; 2024 Your Company. All rights reserved.</p>
    </footer>
</body>
</html>
