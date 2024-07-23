package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.model.UserDTO;
import com.example.service.UserService;

public class RegistrationServlet extends HttpServlet {
    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");

        UserDTO user = new UserDTO(name, email, password, userType);
        boolean isRegistered = userService.registerUser(user);

        if (isRegistered) {
            response.sendRedirect("registrationSuccess.jsp");
        } else {
            response.sendRedirect("registrationError.jsp");
        }
    }
}
