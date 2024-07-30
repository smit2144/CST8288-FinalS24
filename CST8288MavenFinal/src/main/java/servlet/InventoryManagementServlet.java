package servlet;

import java.io.IOException;
import java.sql.Date;

import dao.AdminInventoryDAO;
import dao.AdminInventoryDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.InventoryDTO;

@WebServlet("/manageInventory")
public class InventoryManagementServlet extends HttpServlet {

    private AdminInventoryDAO inventoryDAO;

    @Override
    public void init() throws ServletException {
        inventoryDAO = new AdminInventoryDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "add":
                addInventory(request, response);
                break;
            case "update":
                updateInventory(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
                break;
        }
    }

    private void addInventory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String name = request.getParameter("name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Date expirationDate = Date.valueOf(request.getParameter("expirationDate"));
        String surplusStatus = request.getParameter("surplusStatus");
        String plan = request.getParameter("plan");
        double price = Double.parseDouble(request.getParameter("price"));
        String location = request.getParameter("location");
        String foodGroup = request.getParameter("foodGroup");

        InventoryDTO inventory = new InventoryDTO();
        inventory.setUserID(userId);
        inventory.setName(name);
        inventory.setQuantity(quantity);
        inventory.setExpirationDate(expirationDate);
        inventory.setSurplusStatus(surplusStatus);
        inventory.setPlan(plan);
        inventory.setPrice(price);
        inventory.setLocation(location);
        inventory.setFoodGroup(foodGroup);

        inventoryDAO.addInventory(inventory);
        response.sendRedirect("inventory.jsp"); // Redirect to inventory management page
    }

    private void updateInventory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        String name = request.getParameter("name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Date expirationDate = Date.valueOf(request.getParameter("expirationDate"));
        String surplusStatus = request.getParameter("surplusStatus");
        String plan = request.getParameter("plan");
        double price = Double.parseDouble(request.getParameter("price"));
        String location = request.getParameter("location");
        String foodGroup = request.getParameter("foodGroup");

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

        inventoryDAO.updateInventory(inventory);
        response.sendRedirect("inventory.jsp"); // Redirect to inventory management page
    }
}
