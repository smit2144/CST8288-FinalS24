<!DOCTYPE html>
<html>
<head>
    <title>User Login</title>
</head>
<body>
    <h2>User Login</h2>
    <form action="Login" method="post">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>
        
        <input type="submit" value="Login">
    </form>
    <%
        if (request.getParameter("error") != null) {
            out.println("<p style='color:red;'>Invalid email or password. Please try again.</p>");
        }
    %>
</body>
</html>
