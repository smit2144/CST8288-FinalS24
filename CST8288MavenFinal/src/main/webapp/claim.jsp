<!DOCTYPE html>
<html>
<head>
    <title>Claim Surplus Food</title>
</head>
<body>
    <h2>Claim Surplus Food</h2>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Quantity</th>
            <th>Expiration Date</th>
            <th>Location</th>
            <th>Food Group</th>
            <th>Action</th>
        </tr>
        <c:forEach var="foodItem" items="${surplusFoodItems}">
            <tr>
                <td>${foodItem.name}</td>
                <td>${foodItem.quantity}</td>
                <td>${foodItem.expirationDate}</td>
                <td>${foodItem.location}</td>
                <td>${foodItem.foodGroup}</td>
                <td><a href="ClaimServlet?id=${foodItem.itemId}">Claim</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
