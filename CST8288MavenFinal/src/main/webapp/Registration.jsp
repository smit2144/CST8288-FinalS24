<%@ page import="dao.RegistrationDAOImpl" %>
<%@ page import="model.UserDTO" %>
<%@ page import="service.AppValidator" %>

<!DOCTYPE html>
<html>
<head>
    <title>Registration Page</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 300px;
            margin: 0 auto;
            text-align: left;
            margin-top: 100px;
            background-color: #fff;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            margin-top: 0;
            text-align: center;
        }
        input[type="text"],
        input[type="email"],
        input[type="password"],
        select,
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin: 5px 0;
            border-radius: 5px;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        .register-link {
            margin-top: 10px;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>User Registration</h2>
        <%
            String message = (String) request.getAttribute("message");
            if (message != null) {
                out.println("<h3>" + message + "</h3>");
            }
        %>
        <form action="RegistrationServlet" method="post">
            Name: <input type="text" name="name" required><br>
            Email: <input type="email" name="email" required><br>
            Password: <input type="password" name="password" required><br>
            User Type: 
            <select name="userType">
                <option value="retailer">Retailer</option>
                <option value="consumer">Consumer</option>
                <option value="charitable organization">Charitable Organization</option>
            </select><br>
            <input type="submit" value="Register">
        </form>
        <div class="register-link">
            <p>Already have an account? <a href="index.html">Login</a></p>
        </div>
    </div>
</body>
</html>
