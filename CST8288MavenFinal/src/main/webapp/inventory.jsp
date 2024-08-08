<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.FoodItem" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inventory Management</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <h1>Inventory Management</h1>

    <form action="InventoryServlet" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>
        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" required>
        <label for="expirationDate">Expiration Date:</label>
        <input type="date" id="expirationDate" name="expirationDate" required>
        <label for="surplusStatus">Surplus Status:</label>
        <select id="surplusStatus" name="surplusStatus" required>
            <option value="yes">Yes</option>
            <option value="no">No</option>
        </select>
        <label for="plan">Plan:</label>
        <select id="plan" name="plan" required>
            <option value="keep">Keep</option>
            <option value="donation">Donation</option>
            <option value="sale">Sale</option>
        </select>
        <label for="price">Price:</label>
        <input type="number" step="0.01" id="price" name="price">
        <label for="location">Location:</label>
        <select id="location" name="location" required>
            <option value="Ottawa">Ottawa</option>
            <option value="Toronto">Toronto</option>
            <option value="Montreal">Montreal</option>
        </select>
        <label for="foodGroup">Food Group:</label>
        <select id="foodGroup" name="foodGroup" required>
            <option value="Meat">Meat</option>
            <option value="Vegetable">Vegetable</option>
            <option value="Fruit">Fruit</option>
            <option value="Other">Other</option>
        </select>
        <button type="submit">Add Food Item</button>
    </form>

    <h2>Your Food Items</h2>
    <table>
        <thead>
            <tr>
                <th>Name</th>
                <th>Quantity</th>
                <th>Expiration Date</th>
                <th>Surplus Status</th>
                <th>Plan</th>
                <th>Price</th>
                <th>Location</th>
                <th>Food Group</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<FoodItem> foodItems = (List<FoodItem>) request.getAttribute("foodItems");
                if (foodItems != null) {
                    for (FoodItem item : foodItems) {
            %>
                        <tr>
                            <td><%= item.getName() %></td>
                            <td><%= item.getQuantity() %></td>
                            <td><%= item.getExpirationDate() %></td>
                            <td><%= item.getSurplusStatus() %></td>
                            <td><%= item.getPlan() %></td>
                            <td><%= item.getPrice() %></td>
                            <td><%= item.getLocation() %></td>
                            <td><%= item.getFoodGroup() %></td>
                        </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
</body>
</html>
