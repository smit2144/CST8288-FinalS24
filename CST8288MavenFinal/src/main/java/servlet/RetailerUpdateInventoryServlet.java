package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.RetailerInventoryDAO;
import dao.RetailerInventoryDAOImpl;
import model.InventoryDTO;

@WebServlet("/retailer/updateInventory")
public class RetailerUpdateInventoryServlet extends HttpServlet {

    private RetailerInventoryDAO inventoryDAO;

    @Override
    public void init() throws ServletException {
        inventoryDAO = new RetailerInventoryDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve parameters from the request
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            String name = request.getParameter("name");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            java.util.Date expirationDate = new java.util.Date(request.getParameter("expirationDate"));
            String surplusStatus = request.getParameter("surplusStatus");
            String plan = request.getParameter("plan");
            double price = Double.parseDouble(request.getParameter("price"));
            String location = request.getParameter("location");
            String foodGroup = request.getParameter("foodGroup");

            // Create an InventoryDTO object with the updated data
            InventoryDTO inventory = new InventoryDTO();
            inventory.setItemID(itemId);
            inventory.setName(name);
            inventory.setQuantity(quantity);
            inventory.setExpirationDate(expirationDate);
            inventory.setSurplusStatus(surplusStatus);
            inventory.setPlan(plan);
            inventory.setPrice(price);
            inventory.setLocation(location);
            inventory.setFoodGroup(foodGroup);

            // Perform the update operation
            inventoryDAO.updateInventory(inventory);

            // Redirect to the inventory management page on success
            response.sendRedirect("inventory.jsp");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input format");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while updating the inventory item");
        }
    }
}
