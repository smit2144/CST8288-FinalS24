package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * Servlet for identifying and managing surplus food items.
 * 
 * @author Quelly
 */
@WebServlet("/surplusFood")
public class SurplusFoodServlet extends HttpServlet {
    private AdminInventoryDAO inventoryDAO;

    @Override
    public void init() throws ServletException {
        // Initialize the DAO
        inventoryDAO = new AdminInventoryDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        identifySurplusFood(request, response);
    }

    private void identifySurplusFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<InventoryDTO> allInventory = inventoryDAO.getAllInventory();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();

        for (InventoryDTO item : allInventory) {
            Date expirationDate = item.getExpirationDate();
            if (expirationDate != null && isSurplus(expirationDate, currentDate)) {
                item.setSurplusStatus("Surplus");
                inventoryDAO.updateInventory(item);
            }
        }

        request.setAttribute("message", "Surplus food items identified and updated.");
        response.sendRedirect("listSurplus");
    }

    private boolean isSurplus(Date expirationDate, Date currentDate) {
        long diffInMillis = expirationDate.getTime() - currentDate.getTime();
        long diffInDays = diffInMillis / (1000 * 60 * 60 * 24);
        return diffInDays <= 7;
    }
}
