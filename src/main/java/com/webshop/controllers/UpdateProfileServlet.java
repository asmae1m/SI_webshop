package com.webshop.controllers;

import com.webshop.dao.CustomerDAO;
import com.webshop.models.Customer;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/updateProfile"})
public class UpdateProfileServlet extends HttpServlet {

    private CustomerDAO customerDAO;

    @Override
    public void init() throws ServletException {
        customerDAO = new CustomerDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the current session and customer
        HttpSession session = request.getSession(false);
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Retrieve updated values from the form
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        int age = Integer.parseInt(request.getParameter("age"));

        // Ensure that isAdmin is not updated by the form - preserve the original value

        // Update the customer object with new profile information, except for isAdmin
        customer.setName(name);
        customer.setEmail(email);
        customer.setAddress(address);
        customer.setAge(age);

        // Update customer information in the database
        customerDAO.updateCustomer(customer);

        // Update the session with the new customer information
        session.setAttribute("customer", customer);

        // Redirect back to the edit profile page with a success message
        request.setAttribute("message", "Profile updated successfully!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("editProfile.jsp");
        dispatcher.forward(request, response);
    }
}
