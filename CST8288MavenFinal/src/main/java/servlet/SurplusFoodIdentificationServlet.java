package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.AdminInventoryDAO;
import dao.AdminInventoryDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.InventoryDTO;

@WebServlet("/identifySurplus")
public class SurplusFoodIdentificationServlet extends HttpServlet {

    private AdminInventoryDAO inventoryDAO;

    @Override
    public void init() throws ServletException {
        inventoryDAO = new AdminInventoryDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<InventoryDTO> surplusItems = null;
		try {
			surplusItems = inventoryDAO.getExpiringItemsWithinOneWeek();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        request.setAttribute("surplusItems", surplusItems);
        request.getRequestDispatcher("surplusList.jsp").forward(request, response); // Forward to JSP page to display surplus items
    }
}
