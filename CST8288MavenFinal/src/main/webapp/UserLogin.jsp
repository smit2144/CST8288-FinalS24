<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="login.css">
<title>Login</title>
</head>
<body>
	<h1>Login</h1>
	<form action="<%= request.getContextPath() %>/UserLoginServlet" method="post">
		<label for="email">Email:</label>
		<input type="email" id="email" name="email" required><br><br>
		<label for="password">Password:</label>
		<input type="password" id="password" name="password" required><br><br>
		<button type="submit">Login</button>
	</form>
	<form action="registration.jsp" method="get">
		<button type="submit">Register</button>
	</form>

    <!-- Display alert if there's an error -->
    <%
        String error = request.getParameter("error");
        if (error != null) {
    %>
        <script type="text/javascript">
            // Show alert for invalid email or password
            window.onload = function() {
                alert("Invalid email or password. Please try again.");
            }
        </script>
    <%
        }
    %>
</body>
</html>