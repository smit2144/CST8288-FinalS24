package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.FoodItem;
import service.FoodItemService;

import java.io.IOException;
import java.util.List;

public class InventoryServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5798492123438052549L;
	private FoodItemService foodItemService = new FoodItemService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<FoodItem> foodItems = foodItemService.getAllFoodItems();
        request.setAttribute("foodItems", foodItems);
        request.getRequestDispatcher("inventory.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String expirationDate = request.getParameter("expirationDate");
        String surplusStatus = request.getParameter("surplusStatus");
        String plan = request.getParameter("plan");
        double price = Double.parseDouble(request.getParameter("price"));
        String location = request.getParameter("location");
        String foodGroup = request.getParameter("foodGroup");

        FoodItem foodItem = new FoodItem();
        foodItem.setName(name);
        foodItem.setQuantity(quantity);
        foodItem.setExpirationDate(expirationDate);
        foodItem.setSurplusStatus(surplusStatus);
        foodItem.setPlan(plan);
        foodItem.setPrice(price);
        foodItem.setLocation(location);
        foodItem.setFoodGroup(foodGroup);

        foodItemService.addFoodItem(foodItem);

        response.sendRedirect("InventoryServlet");
    }
}
