package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.RegistrationDAO;
import dao.RegistrationDAOImpl;
import model.UserDTO;

public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RegistrationDAO registrationDAO = new RegistrationDAOImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");

        UserDTO user = new UserDTO(0, name, email, password, userType);

        boolean registrationSuccess = registrationDAO.addUser(user);

        if (registrationSuccess) {
            response.sendRedirect("success.jsp");
        } else {
            response.sendRedirect("register.jsp?error=true");
        }
    }
}
