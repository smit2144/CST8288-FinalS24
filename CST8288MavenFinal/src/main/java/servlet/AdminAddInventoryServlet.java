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

@WebServlet("/admin/addInventory")
public class AdminAddInventoryServlet extends HttpServlet {

    private AdminInventoryDAO inventoryDAO;

    @Override
    public void init() throws ServletException {
        inventoryDAO = new AdminInventoryDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve parameters from the request
            String name = request.getParameter("name");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            java.util.Date expirationDate = new java.util.Date(request.getParameter("expirationDate"));
            String surplusStatus = request.getParameter("surplusStatus");
            String plan = request.getParameter("plan");
            double price = Double.parseDouble(request.getParameter("price"));
            String location = request.getParameter("location");
            String foodGroup = request.getParameter("foodGroup");

            // Create an InventoryDTO object with the data
            InventoryDTO inventory = new InventoryDTO();
            inventory.setName(name);
            inventory.setQuantity(quantity);
            inventory.setExpirationDate(expirationDate);
            inventory.setSurplusStatus(surplusStatus);
            inventory.setPlan(plan);
            inventory.setPrice(price);
            inventory.setLocation(location);
            inventory.setFoodGroup(foodGroup);

            // Perform the addition operation
            inventoryDAO.addInventory(inventory);

            // Redirect to the inventory management page on success
            response.sendRedirect("inventory.jsp");
        } catch (NumberFormatException e) {
            // Handle invalid number format (e.g., quantity, price)
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input format");
        } catch (Exception e) {
            // Handle general exceptions
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while adding the inventory item");
        }
    }
}
