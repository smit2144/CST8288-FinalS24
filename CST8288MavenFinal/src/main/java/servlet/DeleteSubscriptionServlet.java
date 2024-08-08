
package servlet;

import util.DBConnection;
import dao.SubscriptionDAOImpl;
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


@WebServlet(name = "DeleteSubscriptionServlet", urlPatterns = {"/DeleteSubscriptionServlet"})
public class DeleteSubscriptionServlet extends HttpServlet {

    private Connection connection;
    
    public DeleteSubscriptionServlet() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    /**
     * Handles the HTTP POST method.
     * 
     * @param request servlet request containing the subscription ID to be deleted.
     * @param response servlet response to redirect after the deletion operation.
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // Extract subscription ID from the request
        int subscriptionId = Integer.parseInt(request.getParameter("subscriptionId"));
        
        // Optional: Extract user ID from session for additional operations or validation
        int userId = (int) request.getSession().getAttribute("userId");

        try  {
            SubscriptionDAOImpl subscriptionDAO = new SubscriptionDAOImpl();
            
            // Perform the delete operation
            subscriptionDAO.deleteSubscription(subscriptionId);
        } catch (Exception e) {
            // Log exception (production code should replace printStackTrace with logging)
            e.printStackTrace();
        }

        // Redirect to another servlet or JSP to show updated list of subscriptions
        response.sendRedirect("SubscriptionServlet");
    }
}