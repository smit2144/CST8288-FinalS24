<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Expiring Items</title>
 
</head>
<body>
    <header>
        <h1>Expiring Items</h1>
    </header>

    <main>
        <h2>Expiring Items</h2>
        <!-- Table or list to display items nearing expiry -->
        <table>
            <thead>
                <tr>
                    <th>Item Name</th>
                    <th>Quantity</th>
                    <th>Expiry Date</th>
                </tr>
            </thead>
            <tbody>
                <!-- Data rows will be populated here -->
                <tr>
                    <td colspan="3">No expiring items found.</td>
                </tr>
            </tbody>
        </table>
        <p><a href="AdminDashboard.jsp">Back to Dashboard</a></p>
    </main>

    <footer>
        <p>&copy; 2024 Your Company. All rights reserved.</p>
    </footer>
</body>
</html>
