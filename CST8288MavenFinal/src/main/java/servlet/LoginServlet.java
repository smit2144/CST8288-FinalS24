package servlet;
import jakarta.servlet.ServletException;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dao.LoginDAO;
import model.UserDTO;
import java.io.IOException;
import java.sql.SQLException;

/**
*
* @author Hussein Majed
*/

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Call your DAO to check credentials and retrieve user type
        LoginDAO loginDAO;
		try {
			loginDAO = new LoginDAO();
	
        UserDTO user = loginDAO.getUserByEmailAndPassword(email, password);

        if (user != null) {
            // If login is successful, redirect based on user type
            HttpSession session = request.getSession();
            session.setAttribute("user", user); // Store user object in session for future use         
            
            String username = user.getName();
            
            int userId = user.getUserId();
            session.setAttribute("userId",userId); // Store userID in session for future use
            
            String userType = user.getUserType();
            if ("admin".equals(username)) {
                response.sendRedirect("admin_dashboard.jsp");
            }else {
                switch (userType) {
                    case "retailer":
                        response.sendRedirect("retailer_dashboard.jsp");
                        break;
                    case "consumer":
                        response.sendRedirect("consumer_dashboard.jsp");
                        break;
                    case "charitable organization":
                        response.sendRedirect("charity_dashboard.jsp");
                        break;
                    default:
                        // Unknown user type, set error message and forward to login page
                        request.setAttribute("errorMessage", "Unknown user type");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                        break;
                }
            }
            session.setAttribute("userType", userType);// Store userType in session for future use
        } else {
            // If login fails, set error message and forward to login page
            request.setAttribute("errorMessage", "Invalid email or password");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
