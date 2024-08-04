package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.RetailerInventoryDAO;
import dao.RetailerInventoryDAOImpl;

@WebServlet("/retailer/deleteInventory")
public class RetailerDeleteInventoryServlet extends HttpServlet {

    private RetailerInventoryDAO inventoryDAO;

    @Override
    public void init() throws ServletException {
        inventoryDAO = new RetailerInventoryDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve the item ID from the request
            int itemId = Integer.parseInt(request.getParameter("itemId"));

            // Perform the deletion operation
            boolean success = inventoryDAO.deleteInventory(itemId);

            if (success) {
                // Redirect to the inventory management page on success
                response.sendRedirect("inventory.jsp");
            } else {
                // Handle case where item was not found or deletion failed
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Item not found or could not be deleted");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid item ID format");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while deleting the inventory item");
        }
    }
}
