package servlet;

import java.io.IOException;

import dao.AdminInventoryDAO;
import dao.AdminInventoryDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.InventoryDTO;

@WebServlet("/listSurplus")
public class ListSurplusFoodItemsServlet extends HttpServlet {

    private AdminInventoryDAO inventoryDAO;

    @Override
    public void init() throws ServletException {
        inventoryDAO = new AdminInventoryDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        String status = request.getParameter("status"); // "donation" or "sale"

        InventoryDTO inventory = inventoryDAO.getInventoryById(itemId);
        if (inventory != null) {
            inventory.setSurplusStatus(status);
            inventoryDAO.updateInventory(inventory);
        }

        response.sendRedirect("surplusList.jsp"); // Redirect to the page listing surplus items
    }
}
