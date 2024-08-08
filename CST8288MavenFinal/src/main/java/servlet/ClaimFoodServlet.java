=
package servlet;

/**
*
* @author Hussein Majed
*/
import dao.CharityInventoryDAOImpl;
import util.DBConnection;
import dao.TransactionDAOImpl;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.InventoryDTO;
import model.TransactionDTO;
import model.UserDTO;

@WebServlet(name = "ClaimFoodServlet", urlPatterns = {"/ClaimFoodServlet"})
public class ClaimFoodServlet extends HttpServlet {

private Connection connection;
public ClaimFoodServlet() throws SQLException {
    this.connection = DatabaseConnection.getInstance().getConnection();
}


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String itemIdStr = request.getParameter("itemId");
        String quantityStr = request.getParameter("quantity");

        try {
            // Validate and parse itemId
            int itemId = validateAndParseInt(itemIdStr, "Invalid item ID.");
            // Validate and parse quantity
            int requestedQuantity = validateAndParseInt(quantityStr, "Invalid quantity.");

            CharityInventoryDAOImpl inventoryDAO = new CharityInventoryDAOImpl();
            InventoryDTO item = inventoryDAO.getItemById(itemId);
            UserDTO user = (UserDTO) request.getSession().getAttribute("user");

            if (item != null && item.getQuantity() >= requestedQuantity) {
                updateInventoryQuantity(itemId, item.getQuantity() - requestedQuantity);
                
                // Prepare transaction data
                TransactionDTO transaction = new TransactionDTO();
                transaction.setItemId(itemId);
                transaction.setUserId(user.getUserId());
                transaction.setQuantity(requestedQuantity);
                transaction.setAmount(0.00);
                transaction.setDate(new Date()); // Use the current date
                transaction.setTransactionType("donation");

                // Insert transaction
                TransactionDAOImpl transactionDAO = new TransactionDAOImpl();
                transactionDAO.insertTransaction(transaction);
                //claim successful
                response.sendRedirect("charity_view_food.jsp?success=Claim successful.");
            } else {
                response.sendRedirect("charity_view_food.jsp?error=Unable to claim. Requested quantity exceeds available stock or item not found.");
            }
        } catch (IllegalArgumentException e) {
            // Redirect to an error page or send back an error response
            response.sendRedirect("charity_view_food.jsp?error=" + e.getMessage());
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private int validateAndParseInt(String parameterValue, String errorMessage) {
        if (parameterValue == null || parameterValue.isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
        try {
            return Integer.parseInt(parameterValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private void updateInventoryQuantity(int itemId, int newQuantity) {
        try (
             PreparedStatement preparedStatement = connection.prepareStatement(
           "UPDATE fooditems SET Quantity = ? WHERE ItemId = ?")) {
            preparedStatement.setInt(1, newQuantity);
            preparedStatement.setInt(2, itemId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging this exception or handling it more gracefully
        }
    }
}