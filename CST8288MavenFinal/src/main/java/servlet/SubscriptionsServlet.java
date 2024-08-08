package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserDTO;
import service.SubscriptionService;

public class SubscriptionsServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SubscriptionService subscriptionService = new SubscriptionService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");

        String location = request.getParameter("location");
        String foodPreferences = request.getParameter("foodPreferences");
        String alertMethod = request.getParameter("alertMethod");

        subscriptionService.addSubscription(user.getUserId(), location, foodPreferences, alertMethod);
        response.sendRedirect("subscriptions.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("delete") != null && request.getParameter("id") != null) {
            int subscriptionId = Integer.parseInt(request.getParameter("id"));
            subscriptionService.deleteSubscription(subscriptionId);
        }
        response.sendRedirect("subscriptions.jsp");
    }
}
