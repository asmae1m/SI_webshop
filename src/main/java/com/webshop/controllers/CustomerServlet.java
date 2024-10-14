package com.webshop.controllers;

import com.webshop.dao.CustomerDAO;
import com.webshop.models.Customer;
import com.webshop.models.Role;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/customers"})
public class CustomerServlet extends HttpServlet {

    private CustomerDAO customerDAO;

    @Override
    public void init() throws ServletException {
        customerDAO = new CustomerDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null && action.equals("login")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null && action.equals("login")) {
            handleLogin(request, response);
        } else {
            handleRegistration(request, response);
        }
    }

    private void handleRegistration(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String life_activity = request.getParameter("life_activity");
        int age = Integer.parseInt(request.getParameter("age"));
        String password = request.getParameter("password");

        // Set role to USER by default for registration
        Role role = Role.USER;

        // Validate email and password
        if (!isValidEmail(email)) {
            request.setAttribute("message", "Invalid email format.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
            return;
        }

        if (password.length() < 6) {
            request.setAttribute("message", "Password must be at least 6 characters long.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
            return;
        }

        if (customerDAO.getCustomerByEmail(email) != null) {
            request.setAttribute("message", "Email is already registered.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
        } else {
            // Create customer with default role USER
            Customer customer = new Customer(name, email, address, life_activity, age, password, role);
            customerDAO.saveCustomer(customer);

            request.setAttribute("message", "Registration successful!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Customer customer = customerDAO.getCustomerByEmail(email);

        if (customer != null && customer.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("customer", customer);

            response.sendRedirect("products");

        } else {
            request.setAttribute("message", "Invalid email or password.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
