<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Surplus Food Items</title>
</head>
<body>
    <h1>Surplus Food Items</h1>

    <c:if test="${not empty surplusItems}">
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Expiration Date</th>
                    <th>Price</th>
                    <th>Location</th>
                    <th>Food Group</th>
                    <th>Plan</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${surplusItems}">
                    <tr>
                        <td><c:out value="${item.itemID}"/></td>
                        <td><c:out value="${item.name}"/></td>
                        <td><c:out value="${item.quantity}"/></td>
                        <td><c:out value="${item.expirationDate}"/></td>
                        <td><c:out value="${item.price}"/></td>
                        <td><c:out value="${item.location}"/></td>
                        <td><c:out value="${item.foodGroup}"/></td>
                        <td><c:out value="${item.plan}"/></td>
                        <td>
                            <a href="donateOrSell.jsp?itemID=${item.itemID}">Donate/Sell</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty surplusItems}">
        <p>No surplus items available.</p>
    </c:if>
</body>
</html>
