package servlet;

import model.FoodItem;
import service.FoodItemService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateFoodItemServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FoodItemService foodItemService = new FoodItemService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        String name = request.getParameter("name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String expirationDate = request.getParameter("expirationDate");
        String surplusStatus = request.getParameter("surplusStatus");
        String plan = request.getParameter("plan");
        double price = Double.parseDouble(request.getParameter("price"));
        String location = request.getParameter("location");
        String foodGroup = request.getParameter("foodGroup");

        FoodItem foodItem = new FoodItem();
        foodItemService.updateFoodItem(foodItem);
        response.sendRedirect("inventory.jsp");
    }
}
