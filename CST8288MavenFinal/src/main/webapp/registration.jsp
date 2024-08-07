<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>User Registration</title>
</head>
<body>
    <h2>User Registration</h2>

    <!-- Display error message if registration fails -->
    <%
        String error = request.getParameter("error");
        if ("true".equals(error)) {
    %>
        <p style="color:red;">Registration failed. Please try again.</p>
    <%
        }
    %>

    <form action="Register" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br><br>
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>
        
        <label for="userType">User Type:</label>
        <select id="userType" name="userType" required>
            <option value="retailer">Retailer</option>
            <option value="consumer">Consumer</option>
            <option value="charitable organization">Charity</option>
        </select><br><br>
        
        <input type="submit" value="Register">
    </form>
</body>
</html>
