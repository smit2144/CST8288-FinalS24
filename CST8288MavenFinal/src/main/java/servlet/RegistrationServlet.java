package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UserDTO;
import service.UserService;

public class RegistrationServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");

        UserDTO user = new UserDTO(0, name, email, password, userType);
        boolean isRegistered = userService.registerUser(user);

        response.setContentType("text/html");
        if (isRegistered) {
            response.getWriter().println("<h1>Registration Successful</h1>");
        } else {
            response.getWriter().println("<h1>Registration Failed</h1>");
        }
    }
}
