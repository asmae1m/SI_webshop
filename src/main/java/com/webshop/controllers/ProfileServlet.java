package com.webshop.controllers;

import com.webshop.models.Customer;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/editProfile"})
public class ProfileServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check if the customer is logged in
        HttpSession session = request.getSession(false);
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer == null) {
            // If not logged in, redirect to login page
            response.sendRedirect("login.jsp");
            return;
        }

        // Forward customer information to the editProfile.jsp
        request.setAttribute("customer", customer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("editProfile.jsp");
        dispatcher.forward(request, response);
    }
}
