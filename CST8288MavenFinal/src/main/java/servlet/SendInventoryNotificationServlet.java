/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlet;

import util.DBConnection;
import model.InventoryDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
/**
*
* @author Hussein Majed
*/
@WebServlet(name = "SendInventoryNotificationServlet", urlPatterns = {"/SendInventoryNotificationServlet"})
public class SendInventoryNotificationServlet extends HttpServlet {

    private Connection connection;
    
    public SendInventoryNotificationServlet() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }
    /**
     * Handles the HTTP POST method by retrieving inventory information from the request,
     * validating it, and sending notifications to subscribers.
     * 
     * @param request servlet request that contains the inventory data.
     * @param response servlet response that redirects to a JSP page with a notification status message.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Attempt to retrieve inventory information from the request
        InventoryDTO inventory = (InventoryDTO) request.getAttribute("inventory");

        // If inventory information is not present, return a BAD REQUEST response
        if (inventory == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No inventory information provided.");
            return;
        }

        // Attempt to establish a database connection and create a notification service instance
        try {
            InventoryNotificationService notificationService = new InventoryNotificationService(connection);
            
            // Use the notification service to send notifications to subscribers
            boolean notificationsSent = notificationService.notifySubscribers(inventory);

            // Prepare a message based on whether notifications were successfully sent
            String message = notificationsSent
                             ? "Notification sent successfully for subscribers of " + inventory.getFoodGroup() + " in " + inventory.getLocation() + "."
                             : "No matching subscriptions were found for the new inventory.";
            request.setAttribute("notificationMessage", message);

        } catch (Exception e) {
            // Log the exception and prepare an error message for the response
            e.printStackTrace();
            request.setAttribute("notificationMessage", "Error during notification process: " + e.getMessage());
        }
        
        // Forward the request to a JSP page to display the notification status message
        request.getRequestDispatcher("/retailer_addInventory.jsp").forward(request, response);
    }
}