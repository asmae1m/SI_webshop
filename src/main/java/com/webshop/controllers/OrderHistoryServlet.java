package com.webshop.controllers;

import com.webshop.dao.OrderDAO;
import com.webshop.models.Customer;
import com.webshop.models.Order;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/orderHistory")
public class OrderHistoryServlet extends HttpServlet {

    private OrderDAO orderDAO;

    @Override
    public void init() throws ServletException {
        orderDAO = new OrderDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Order> orders = orderDAO.getOrdersByCustomer(customer);
        request.setAttribute("orders", orders);

        RequestDispatcher dispatcher = request.getRequestDispatcher("orderHistory.jsp");
        dispatcher.forward(request, response);
    }
}
