<!DOCTYPE html>
<html>
<head>
    <title>Purchase Food</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
    
</head>
<body>
    <h2>Purchase Food</h2>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Quantity</th>
            <th>Expiration Date</th>
            <th>Price</th>
            <th>Location</th>
            <th>Food Group</th>
            <th>Action</th>
        </tr>
        <c:forEach var="foodItem" items="${foodItemsForSale}">
            <tr>
                <td>${foodItem.name}</td>
                <td>${foodItem.quantity}</td>
                <td>${foodItem.expirationDate}</td>
                <td>${foodItem.price}</td>
                <td>${foodItem.location}</td>
                <td>${foodItem.foodGroup}</td>
                <td><a href="PurchaseServlet?id=${foodItem.itemId}">Purchase</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
