package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.AdminInventoryDAO;
import dao.AdminInventoryDAOImpl;
import model.InventoryDTO;

@WebServlet("/admin/editInventory")
public class AdminEditInventoryServlet extends HttpServlet {

    private AdminInventoryDAO inventoryDAO;

    @Override
    public void init() throws ServletException {
        inventoryDAO = new AdminInventoryDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve item ID from the request
            int itemId = Integer.parseInt(request.getParameter("itemId"));

            // Fetch the inventory item from the database
            InventoryDTO inventory = inventoryDAO.getInventoryById(itemId);

            if (inventory != null) {
                // Set inventory object as a request attribute
                request.setAttribute("inventory", inventory);
                // Forward to the edit inventory page
                request.getRequestDispatcher("/admin/editInventory.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Item not found");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid item ID format");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while fetching the inventory item");
        }
    }
}
