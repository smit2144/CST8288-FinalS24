<!DOCTYPE html>
<html>
<head>
    <title>User Registration</title>
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
            <option value="charity">Charity</option>
        </select><br><br>
        
        <input type="submit" value="Register">
    </form>
</body>
</html>
