package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dao.UserDAOImpl;
import model.User;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    
    private UserDAOImpl userLogin;

    
    @Override
    public void init() throws ServletException {
        userLogin = new UserDAOImpl();
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("Views/login.jsp");
    }


  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = userLogin.validateUser(email, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("username", user.getUserName());  // Store the username in the session
                
                String userType = user.getUserType();
                if ("Retailer".equals(userType)) {
                    response.sendRedirect("Views/retailerHomepage.jsp");
                } else if ("Consumer".equals(userType)) {
                    response.sendRedirect("Views/consumerHomepage.jsp");
                } else if ("Charitable Organization".equals(userType)) {
                    response.sendRedirect("Views/charityHomepage.jsp");
                } else {
                    response.sendRedirect("Views/login.jsp?error=Invalid user type");
                }
            } else {
                response.sendRedirect("Views/login.jsp?error=Invalid email or password");
            }
        } catch (SQLException e) {
            throw new ServletException("Error validating user", e);
        }
    }
}
