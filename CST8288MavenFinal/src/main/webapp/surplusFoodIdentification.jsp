<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.InventoryDTO" %>
<%@ page import="java.util.List" %> <!-- Import the List class -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Surplus Food Identification</title>
</head>
<body>
    <h2>Surplus Food Identification</h2>
    
    <form action="IdentifySurplus" method="post">
        <label for="todayDate">Select Date:</label>
        <input type="date" id="todayDate" name="todayDate" required><br><br>
        
        <input type="submit" value="Identify Surplus Items">
    </form>
    
    <h3>Surplus Food Items</h3>
    <table border="1">
        <thead>
            <tr>
                <th>Item ID</th>
                <th>Name</th>
                <th>Quantity</th>
                <th>Expiration Date</th>
                <th>Surplus Status</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<InventoryDTO> surplusItems = (List<InventoryDTO>) request.getAttribute("surplusItems");
                if (surplusItems != null) {
                    for (InventoryDTO item : surplusItems) {
            %>
            <tr>
                <td><%= item.getItemID() %></td>
                <td><%= item.getName() %></td>
                <td><%= item.getQuantity() %></td>
                <td><%= item.getExpirationDate() %></td>
                <td><%= item.getSurplusStatus() %></td>
            </tr>
            <% 
                    }
                } else {
            %>
            <tr>
                <td colspan="5">No surplus items found.</td>
            </tr>
            <% 
                }
            %>
        </tbody>
    </table>
</body>
</html>
