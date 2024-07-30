<%@ page import="java.util.List, model.InventoryDTO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Surplus Food List</title>
</head>
<body>
    <h1>Surplus Food List</h1>

    <form action="listSurplus" method="post">
        <input type="hidden" name="action" value="updateStatus">
        <h2>Update Surplus Status</h2>
        <label for="itemId">Item ID:</label>
        <input type="number" id="itemId" name="itemId" required><br>
        <label for="status">Status:</label>
        <select id="status" name="status">
            <option value="donation">Donation</option>
            <option value="sale">Sale</option>
        </select><br>
        <button type="submit">Update Status</button>
    </form>

    <h2>Surplus Items</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Quantity</th>
                <th>Expiration Date</th>
                <th>Status</th>
                <th>Price</th>
                <th>Location</th>
                <th>Food Group</th>
                <th>Plan</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<InventoryDTO> surplusItems = (List<InventoryDTO>) request.getAttribute("surplusItems");
                for (InventoryDTO item : surplusItems) {
            %>
            <tr>
                <td><%= item.getItemID() %></td>
                <td><%= item.getName() %></td>
                <td><%= item.getQuantity() %></td>
                <td><%= item.getExpirationDate() %></td>
                <td><%= item.getSurplusStatus() %></td>
                <td><%= item.getPrice() %></td>
                <td><%= item.getLocation() %></td>
                <td><%= item.getFoodGroup() %></td>
                <td><%= item.getPlan() %></td>
            </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
