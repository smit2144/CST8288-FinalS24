
package servlet;

import dao.AdminInventoryDAOImpl;
import dao.RetailerInventoryDAOImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Hussein Majed
 */
@WebServlet(name = "Admin_DeleteInventoryServlet", urlPatterns = {"/Admin_DeleteInventoryServlet"})
public class Admin_DeleteInventoryServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve item ID from request parameters
        int itemId = Integer.parseInt(request.getParameter("itemId"));

     
        try {
        	// Call DAO method to delete inventory
            AdminInventoryDAOImpl adminInventoryDAO = new AdminInventoryDAOImpl();
            adminInventoryDAO.deleteInventory(itemId); 
            // Set success message attribute in request
            request.setAttribute("successMessage", "Inventory deleted successfully");
            
        } catch (Exception e) {
            // Set error message attribute in request
            request.setAttribute("errorMessage", "Failed to delete inventory: " + e.getMessage());
        }

        
        request.getRequestDispatcher("admin_dashboard.jsp").forward(request, response);

    }
}