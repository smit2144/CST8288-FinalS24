<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Inventory</title>
</head>
<body>
    <header>
        <h1>Add Inventory</h1>
    </header>

    <main>
        <h2>Add New Inventory Item</h2>
        <form action="/servlet/AdminAddInventory" method="post">
            <!-- Form fields to add new inventory -->
        <label for="name">Item Name:</label>
		<input type="text" id="name" name="name" required><br>

		<label for="quantity">Quantity:</label>
		<input type="number" id="quantity" name="quantity" required><br>

		<label for="expirationDate">Expiry Date:</label>
		<input type="date" id="expirationDate" name="expirationDate" required><br>
	
		<label for="surplusStatus">Surplus Status:</label>
		<input type="text" id="surplusStatus" name="surplusStatus"><br>

		<label for="plan">Plan:</label>
		<input type="text" id="plan" name="plan"><br>

		<label for="price">Price:</label>
		<input type="number" step="0.01" id="price" name="price"><br>

		<label for="location">Location:</label>
		<input type="text" id="location" name="location"><br>

		<label for="foodGroup">Food Group:</label>
		<input type="text" id="foodGroup" name="foodGroup"><br>

            <button type="submit">Add Item</button>
        </form>
        <p><a href="AdminDashboard.jsp">Back to Dashboard</a></p>
    </main>

    <footer>
        <p>&copy; 2024 Your Company. All rights reserved.</p>
    </footer>
</body>
</html>
