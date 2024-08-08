
package servlet;

import dao.AdminInventoryDAOImpl;
import dao.RetailerInventoryDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
*
* @author Hussein Majed
*/
@WebServlet(name = "Retailer_DeleteInventoryServlet", urlPatterns = {"/Retailer_DeleteInventoryServlet"})
public class Retailer_DeleteInventoryServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve item ID from request parameters
        int itemId = Integer.parseInt(request.getParameter("itemId"));

    
        try {
            RetailerInventoryDAOImpl retailerInventoryDAO = new RetailerInventoryDAOImpl();
            retailerInventoryDAO.deleteInventory(itemId); 
            // Set success message attribute in request
            request.setAttribute("successMessage", "Inventory deleted successfully");
            
        } catch (Exception e) {
            // Set error message attribute in request
            request.setAttribute("errorMessage", "Failed to delete inventory: " + e.getMessage());
        }

        
        request.getRequestDispatcher("retailer_dashboard.jsp").forward(request, response);

    }
}
