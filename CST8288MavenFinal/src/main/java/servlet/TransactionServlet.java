
package servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dao.TransactionDAOImpl;
import model.UserDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import model.TransactionDTO;
import dao.TransactionDAOImpl;
/**
*
* @author Hussein Majed
*/
@WebServlet(name = "TransactionServlet", urlPatterns = {"/transactions"})
public class TransactionServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Don't create a new session

        if (session != null && session.getAttribute("user") != null) {
            UserDTO user = (UserDTO) session.getAttribute("user"); // Retrieve the UserDTO object
            int userId = user.getUserId(); // Use getUserId() to get the user's ID

            // Assuming you have a DAO method to get transactions by user ID
            TransactionDAOImpl transactionDAO = null;
			try {
				transactionDAO = new TransactionDAOImpl();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            List<TransactionDTO> transactions = transactionDAO.getTransactionsByUserId(userId);

            // Set the transactions as an attribute to request and forward to JSP
            request.setAttribute("transactions", transactions);
            
            if (user.getUserType().equals("charitable organization")) {
                request.getRequestDispatcher("/WEB-INF/charity_trans.jsp").forward(request, response);
            } else if (user.getUserType().equals("consumer")) {
                request.getRequestDispatcher("/WEB-INF/consumer_trans.jsp").forward(request, response);
            } else {
                // Handle other user types or an error condition
                response.sendRedirect("login.jsp");
            }
           
        }
    }
}
