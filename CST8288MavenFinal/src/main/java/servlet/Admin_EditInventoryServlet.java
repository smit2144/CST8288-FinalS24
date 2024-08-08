/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
*
* @author Hussein Majed
*/
@WebServlet(name = "Admin_EditInventoryServlet", urlPatterns = {"/Admin_EditInventoryServlet"})
public class Admin_EditInventoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Retrieve item ID from request parameters
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        
        // Redirect to a page where users can edit the inventory item with the given ID
        response.sendRedirect("admin_update.jsp?itemId=" + itemId);
    }
}