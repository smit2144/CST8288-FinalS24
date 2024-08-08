package servlet;

import businesslayer.AppValidator;
import dao.AdminInventoryDAOImpl;
import dao.RetailerInventoryDAOImpl;
import model.InventoryDTO;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
*
* @author Hussein Majed
*/

@WebServlet(name = "Admin_AddInventoryServlet", urlPatterns = {"/Admin_AddInventoryServlet"})
public class Admin_AddInventoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // Retrieve form parameters
        String name = request.getParameter("name");
        int userId = Integer.parseInt(request.getParameter("userId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Date expirationDate = Date.valueOf(request.getParameter("expirationDate"));
        String surplusStatus = request.getParameter("surplusStatus");
        String plan = request.getParameter("plan");
        double price = Double.parseDouble(request.getParameter("price"));
        String location = request.getParameter("location"); // Retrieve location parameter
        String foodGroup = request.getParameter("foodGroup"); // Retrieve food group parameter

        // Create an InventoryDTO object with form data
        InventoryDTO inventory = new InventoryDTO();
        inventory.setUserId(userId);
        inventory.setName(name);
        inventory.setQuantity(quantity);
        inventory.setExpirationDate(expirationDate);
        inventory.setSurplusStatus(surplusStatus);
        inventory.setPlan(plan);
        inventory.setPrice(price);
        inventory.setLocation(location); // Set location in the InventoryDTO object
        inventory.setFoodGroup(foodGroup); // Set food group in the InventoryDTO object

        // Validate the inventory using AppValidator or custom validation logic
        boolean isValidInventory = AppValidator.isValidInventory(inventory);

//        if (isValidInventory) {
//            // Additional validation for surplus status and plan
//            if ((surplusStatus.equals("yes") && (plan.equals("donation") || plan.equals("sale"))) ||(surplusStatus.equals("no"))){
//                if ((plan.equals("donation") && price == 0.0) || (plan.equals("sale") && price != 0.0)||(plan.equals("keep") && price == 0.0)) {
//                    // Call DAO method to add inventory
//                  
                    try {
                    	RetailerInventoryDAOImpl retailerInventoryDAO = new RetailerInventoryDAOImpl();
                        retailerInventoryDAO.addInventory(inventory);
                        // Set success message attribute in request
                        request.setAttribute("successMessage", "Inventory added successfully");
                        // Redirect back to retailer home page after adding inventory
                    } catch (Exception e) {
                    	System.out.println(e.getLocalizedMessage());
                    }
//                        // Set error message attribute in request
//                        request.setAttribute("errorMessage", "Failed to add inventory: " + e.getMessage());
//                    }
//                } else {
//                    request.setAttribute("errorMessage", "Invalid plan and price combination.");               
//                }
//            } else {
//                // Invalid surplus status and plan combination
//                request.setAttribute("errorMessage", "Invalid surplus status and plan combination.");
//            }
//        } else {
//            // If the inventory is not valid, display error message
//            request.setAttribute("errorMessage", "Invalid inventory data. Please check your inputs.");
//        }
        // Forward request back to add inventory page
        request.getRequestDispatcher("admin_addInventory.jsp").forward(request, response);
    }
}