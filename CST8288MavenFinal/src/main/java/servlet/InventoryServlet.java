package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import dao.AdminInventoryDAO;
import dao.AdminInventoryDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.InventoryDTO;

/**
 * Servlet for managing inventory items. Handles CRUD operations for inventory items.
 * 
 * @author Quelly
 */
@WebServlet("/inventory")
public class InventoryServlet extends HttpServlet {
    private AdminInventoryDAO inventoryDAO;

    @Override
    public void init() throws ServletException {
        // Initialize the DAO
        inventoryDAO = new AdminInventoryDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listInventory(request, response);
                break;
            case "details":
                showInventoryDetails(request, response);
                break;
            default:
                listInventory(request, response);
                break;
        }
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
            case "delete":
                deleteInventory(request, response);
                break;
            default:
                listInventory(request, response);
                break;
        }
    }

    private void listInventory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<InventoryDTO> inventoryList = inventoryDAO.getAllInventory();
        request.setAttribute("inventoryList", inventoryList);
        request.getRequestDispatcher("/inventoryList.jsp").forward(request, response);
    }

    private void showInventoryDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            InventoryDTO inventory = inventoryDAO.getInventoryById(itemId);
            request.setAttribute("inventory", inventory);
            request.getRequestDispatcher("/inventoryDetails.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid item ID.");
            listInventory(request, response);
        }
    }

    private void addInventory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int userID = Integer.parseInt(request.getParameter("userID"));
            String name = request.getParameter("name");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date expirationDate = sdf.parse(request.getParameter("expirationDate"));
            String surplusStatus = request.getParameter("surplusStatus");
            String plan = request.getParameter("plan");
            double price = Double.parseDouble(request.getParameter("price"));
            String location = request.getParameter("location");
            String foodGroup = request.getParameter("foodGroup");

            InventoryDTO inventory = new InventoryDTO();
            inventory.setUserID(userID);
            inventory.setName(name);
            inventory.setQuantity(quantity);
            inventory.setExpirationDate(expirationDate);
            inventory.setSurplusStatus(surplusStatus);
            inventory.setPlan(plan);
            inventory.setPrice(price);
            inventory.setLocation(location);
            inventory.setFoodGroup(foodGroup);

            inventoryDAO.addInventory(inventory);

            request.setAttribute("message", "Inventory item added successfully.");
        } catch (NumberFormatException | ParseException e) {
            request.setAttribute("error", "Error adding inventory item: " + e.getMessage());
        }

        response.sendRedirect("inventory?action=list");
    }

    private void updateInventory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int itemID = Integer.parseInt(request.getParameter("itemID"));
            int userID = Integer.parseInt(request.getParameter("userID"));
            String name = request.getParameter("name");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date expirationDate = sdf.parse(request.getParameter("expirationDate"));
            String surplusStatus = request.getParameter("surplusStatus");
            String plan = request.getParameter("plan");
            double price = Double.parseDouble(request.getParameter("price"));
            String location = request.getParameter("location");
            String foodGroup = request.getParameter("foodGroup");

            InventoryDTO inventory = new InventoryDTO();
            inventory.setItemID(itemID);
            inventory.setUserID(userID);
            inventory.setName(name);
            inventory.setQuantity(quantity);
            inventory.setExpirationDate(expirationDate);
            inventory.setSurplusStatus(surplusStatus);
            inventory.setPlan(plan);
            inventory.setPrice(price);
            inventory.setLocation(location);
            inventory.setFoodGroup(foodGroup);

            inventoryDAO.updateInventory(inventory);

            request.setAttribute("message", "Inventory item updated successfully.");
        } catch (NumberFormatException | ParseException e) {
            request.setAttribute("error", "Error updating inventory item: " + e.getMessage());
        }

        response.sendRedirect("inventory?action=list");
    }

    private void deleteInventory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int itemID = Integer.parseInt(request.getParameter("itemID"));

            inventoryDAO.deleteInventory(itemID);

            request.setAttribute("message", "Inventory item deleted successfully.");
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Error deleting inventory item: Invalid item ID.");
        }

        response.sendRedirect("inventory?action=list");
    }
}
