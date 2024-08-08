<!DOCTYPE html>
<html>
<head>
    <title>User Registration</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
    
</head>
<body>
    <h2>User Registration</h2>
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
            <option value="charitable organization">Charitable Organization</option>
        </select><br><br>
        
        <input type="submit" value="Register">
    </form>
    <%
        if (request.getParameter("error") != null) {
            out.println("<p style='color:red;'>Registration failed. Please try again.</p>");
        }
    %>
</body>
</html>
