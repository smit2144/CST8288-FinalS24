<!DOCTYPE html>
<html>
<head>
    <title>Update Food Item</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
    
</head>
<body>
    <h2>Update Food Item</h2>
    <form action="updateFoodItem" method="post">
        <input type="hidden" id="itemId" name="itemId" value="${foodItem.itemId}">
        
        <label for="userId">User ID:</label>
        <input type="text" id="userId" name="userId" value="${foodItem.userId}" required><br><br>
        
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${foodItem.name}" required><br><br>
        
        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" value="${foodItem.quantity}" required><br><br>
        
        <label for="expirationDate">Expiration Date:</label>
        <input type="date" id="expirationDate" name="expirationDate" value="${foodItem.expirationDate}" required><br><br>
        
        <label for="surplusStatus">Surplus Status:</label>
        <select id="surplusStatus" name="surplusStatus" required>
            <option value="yes" ${foodItem.surplusStatus == 'yes' ? 'selected' : ''}>Yes</option>
            <option value="no" ${foodItem.surplusStatus == 'no' ? 'selected' : ''}>No</option>
        </select><br><br>
        
        <label for="plan">Plan:</label>
        <select id="plan" name="plan" required>
            <option value="keep" ${foodItem.plan == 'keep' ? 'selected' : ''}>Keep</option>
            <option value="donation" ${foodItem.plan == 'donation' ? 'selected' : ''}>Donation</option>
            <option value="sale" ${foodItem.plan == 'sale' ? 'selected' : ''}>Sale</option>
        </select><br><br>
        
        <label for="price">Price:</label>
        <input type="text" id="price" name="price" value="${foodItem.price}"><br><br>
        
        <label for="location">Location:</label>
        <select id="location" name="location" required>
            <option value="Ottawa" ${foodItem.location == 'Ottawa' ? 'selected' : ''}>Ottawa</option>
            <option value="Toronto" ${foodItem.location == 'Toronto' ? 'selected' : ''}>Toronto</option>
            <option value="Montreal" ${foodItem.location == 'Montreal' ? 'selected' : ''}>Montreal</option>
        </select><br><br>
        
        <label for="foodGroup">Food Group:</label>
        <select id="foodGroup" name="foodGroup" required>
            <option value="Meat" ${foodItem.foodGroup == 'Meat' ? 'selected' : ''}>Meat</option>
            <option value="Vegetable" ${foodItem.foodGroup == 'Vegetable' ? 'selected' : ''}>Vegetable</option>
            <option value="Fruit" ${foodItem.foodGroup == 'Fruit' ? 'selected' : ''}>Fruit</option>
            <option value="Other" ${foodItem.foodGroup == 'Other' ? 'selected' : ''}>Other</option>
        </select><br><br>
        
        <input type="submit" value="Update Food Item">
    </form>
</body>
</html>
