package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.FoodItem;
import model.UserDTO;
import service.InventoryService;

public class InventoryServlet extends HttpServlet {
    private InventoryService inventoryService = new InventoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<FoodItem> foodItems = null;
		try {
			foodItems = inventoryService.getAllFoodItems();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("foodItems", foodItems);
		request.getRequestDispatcher("inventory.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String name = request.getParameter("name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String expirationDateStr = request.getParameter("expirationDate");
        String surplusStatus = request.getParameter("surplusStatus");
        String plan = request.getParameter("plan");
        double price = Double.parseDouble(request.getParameter("price"));
        String location = request.getParameter("location");
        String foodGroup = request.getParameter("foodGroup");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date expirationDate = null;
        try {
            expirationDate = sdf.parse(expirationDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            request.setAttribute("error", "Invalid expiration date format.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        FoodItem foodItem = new FoodItem();
        foodItem.setName(name);
        foodItem.setQuantity(quantity);
        foodItem.setExpirationDate(new java.sql.Date(expirationDate.getTime()).toString());
        foodItem.setSurplusStatus(surplusStatus);
        foodItem.setPlan(plan);
        foodItem.setPrice(price);
        foodItem.setLocation(location);
        foodItem.setFoodGroup(foodGroup);
        foodItem.setUserID(user.getUserId());

        try {
            inventoryService.addFoodItem(foodItem);
            response.sendRedirect("inventory.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Unable to add food item.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
