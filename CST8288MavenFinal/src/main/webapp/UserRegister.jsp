<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<link rel="stylesheet" type="text/css" href="registration.css">
<script>
    function toggleSubscription() {
        var userType = document.getElementById("user_type").value;
        var subscribeSection = document.getElementById("subscribeSection");

        if (userType === "Consumer" || userType === "Charitable Organization") {
            subscribeSection.style.display = "block";
        } else {
            subscribeSection.style.display = "none";
        }
    }

    window.onload = function() {
        toggleSubscription();
    };
</script>
</head>
<body>
    <h1>Register</h1>
    <form action="<%= request.getContextPath() %>/UserRegister" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>
        <label for="phone">Phone:</label>
        <input type="tel" id="phone" name="phone" required><br><br>
        <label for="user_type">User Type:</label>
        <select id="user_type" name="user_type" required onchange="toggleSubscription()">
            <option value="Retailer">Retailer</option>
            <option value="Consumer">Consumer</option>
            <option value="Charitable Organization">Charitable Organization</option>
        </select><br><br>
        <div id="subscribeSection" style="display:none;">
            <input type="checkbox" id="subscribe" name="subscribe" value="true">
            <label for="subscribe" style="display: inline;">Subscribe for Notifications</label><br><br>
        </div>
        <button type="submit">Register</button>
    </form>
</body>
</html>
