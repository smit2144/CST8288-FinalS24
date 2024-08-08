<!DOCTYPE html>
<html>
<head>
    <title>Manage Subscriptions</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
    
</head>
<body>
    <h2>Manage Subscriptions</h2>
    <form action="SubscriptionsServlet" method="post">
        <label for="location">Location:</label>
        <input type="text" id="location" name="location" required><br><br>

        <label for="foodPreferences">Food Preferences:</label>
        <input type="text" id="foodPreferences" name="foodPreferences"><br><br>

        <label for="alertMethod">Alert Method:</label>
        <select id="alertMethod" name="alertMethod" required>
            <option value="email">Email</option>
            <option value="phone">Phone</option>
        </select><br><br>

        <input type="submit" value="Save Subscription">
    </form>

    <h2>Current Subscriptions</h2>
    <table border="1">
        <tr>
            <th>Location</th>
            <th>Food Preferences</th>
            <th>Alert Method</th>
            <th>Action</th>
        </tr>
        <c:forEach var="subscription" items="${subscriptions}">
            <tr>
                <td>${subscription.location}</td>
                <td>${subscription.foodPreferences}</td>
                <td>${subscription.alertMethod}</td>
                <td><a href="SubscriptionsServlet?delete=true&id=${subscription.subscriptionId}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
