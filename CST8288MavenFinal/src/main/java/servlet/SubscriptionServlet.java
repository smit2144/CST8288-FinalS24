/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;


import dao.SubscriptionDAOImpl;
import util.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.SubscriptionDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
*
* @author Hussein Majed
*/
@WebServlet(name = "SubscriptionServlet", urlPatterns = {"/SubscriptionServlet"})
public class SubscriptionServlet extends HttpServlet {

    private Connection connection;
    
    public SubscriptionServlet() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }
    
    /**
     * Handles the HTTP POST request to add a new subscription.
     *
     * @param request  HttpServletRequest object that contains the request the client made of the servlet.
     * @param response HttpServletResponse object that contains the response the servlet returns to the client.
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = (int) request.getSession().getAttribute("userId");
        String location = request.getParameter("location");
        String foodPreferences = request.getParameter("foodPreferences");
        String alertMethod = request.getParameter("alertMethod");

        SubscriptionDTO subscription = new SubscriptionDTO();
        subscription.setUserId(userId);
        subscription.setLocation(location);
        subscription.setFoodPreferences(foodPreferences);
        subscription.setAlertMethod(alertMethod);

        try  {
            SubscriptionDAOImpl subscriptionDAO = new SubscriptionDAOImpl();
            boolean subscriptionStatus = subscriptionDAO.addSubscription(subscription);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();

            if (subscriptionStatus) {
                out.write("{\"success\": true}");
            } else {
                out.write("{\"success\": false}");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the HTTP GET request to retrieve the current user's subscriptions.
     *
     * @param request  HttpServletRequest object that contains the request the client made of the servlet.
     * @param response HttpServletResponse object that contains the response the servlet returns to the client.
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = (int) request.getSession().getAttribute("userId");

        List<SubscriptionDTO> subscriptions = new ArrayList<>();
        try {
            SubscriptionDAOImpl subscriptionDAO = new SubscriptionDAOImpl();
            subscriptions = subscriptionDAO.getSubscriptionsByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("subscriptions", subscriptions);
        // Forward the request to the appropriate JSP page to display the subscription list
        // You might need to decide which JSP page to forward based on the session's user type or roles
        request.getRequestDispatcher("consumer_sub_history.jsp").forward(request, response);
    }
}