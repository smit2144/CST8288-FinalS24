package servlet;

import java.io.IOException;
import java.util.List;

import dao.AdminInventoryDAO;
import dao.AdminInventoryDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.InventoryDTO;

/**
 * Servlet for listing surplus food items.
 * 
 * @author Quelly
 */
@WebServlet("/listSurplus")
public class ListSurplusServlet extends HttpServlet {
    private AdminInventoryDAO inventoryDAO;

    @Override
    public void init() throws ServletException {
        // Initialize the DAO
        inventoryDAO = new AdminInventoryDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<InventoryDTO> surplusItems = inventoryDAO.getSurplusInventory();
        request.setAttribute("surplusItems", surplusItems);
        request.getRequestDispatcher("/listSurplus.jsp").forward(request, response);
    }
}
