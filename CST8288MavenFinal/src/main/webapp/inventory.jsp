<!DOCTYPE html>
<html>
<head>
    <title>Inventory</title>
</head>
<body>
    <h2>Add Food Item</h2>
    <form action="InventoryServlet" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br><br>
        
        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" required><br><br>
        
        <label for="expirationDate">Expiration Date:</label>
        <input type="date" id="expirationDate" name="expirationDate" required><br><br>
        
        <label for="surplusStatus">Surplus Status:</label>
        <select id="surplusStatus" name="surplusStatus" required>
            <option value="yes">Yes</option>
            <option value="no">No</option>
        </select><br><br>
        
        <label for="plan">Plan:</label>
        <select id="plan" name="plan" required>
            <option value="keep">Keep</option>
            <option value="donation">Donation</option>
            <option value="sale">Sale</option>
        </select><br><br>
        
        <label for="price">Price:</label>
        <input type="number" step="0.01" id="price" name="price"><br><br>
        
        <label for="location">Location:</label>
        <select id="location" name="location" required>
            <option value="Ottawa">Ottawa</option>
            <option value="Toronto">Toronto</option>
            <option value="Montreal">Montreal</option>
        </select><br><br>
        
        <label for="foodGroup">Food Group:</label>
        <select id="foodGroup" name="foodGroup" required>
            <option value="Meat">Meat</option>
            <option value="Vegetable">Vegetable</option>
            <option value="Fruit">Fruit</option>
            <option value="Other">Other</option>
        </select><br><br>
        
        <input type="submit" value="Add Food Item">
    </form>

    <h2>Inventory List</h2>
    <c:if test="${not empty foodItems}">
        <ul>
            <c:forEach var="item" items="${foodItems}">
                <li>${item.name} - ${item.quantity} - ${item.expirationDate}</li>
            </c:forEach>
        </ul>
    </c:if>
    <c:if test="${empty foodItems}">
        <p>No food items found.</p>
    </c:if>
</body>
</html>
