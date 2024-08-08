
package servlet;

import businesslayer.AppValidator;
import util.DBConnection;
import dao.RegistrationDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UserDTO;
/**
*
* @author Hussein Majed
*/
@WebServlet(name = "RegistrationServlet", urlPatterns = { "/RegistrationServlet" })
public class RegistrationServlet extends HttpServlet {

	Connection connection;

	public RegistrationServlet() {
		super();
		connection = DatabaseConnection.getInstance().getConnection();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String userType = request.getParameter("userType");

		// Create a UserDTO object with form data
		UserDTO user = new UserDTO();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		user.setUserType(userType);

		// Validate user input
		boolean isValidUser = AppValidator.isValidUser(user);

		if (isValidUser) {

			try {
				var registrationDAO = new RegistrationDAOImpl(connection);
				boolean registrationStatus = registrationDAO.registerUser(user);
				if (registrationStatus) {
					request.setAttribute("registrationSuccess", true);

				} else {
					// Set attributes for invalid input
					if (!AppValidator.isValidName(user)) {
						request.setAttribute("invalidName", true);
					}
					if (!AppValidator.isValidEmail(user)) {
						request.setAttribute("invalidEmail", true);
					}
					if (!AppValidator.isValidPassword(user)) {
						request.setAttribute("invalidPassword", true);
					}

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Forward the request to registration.jsp for displaying messages
			request.getRequestDispatcher("/Registration.jsp").forward(request, response);
		}
	}

}