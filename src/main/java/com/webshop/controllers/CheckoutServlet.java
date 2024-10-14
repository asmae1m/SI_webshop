package com.webshop.controllers;

import com.webshop.dao.OrderDAO;
import com.webshop.dao.ProductDAO;
import com.webshop.models.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    private OrderDAO orderDAO;
    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        orderDAO = new OrderDAO();
        productDAO = new ProductDAO();
    }

    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Customer customer = (Customer) session.getAttribute("customer");
        Map<Long, Integer> cart = (Map<Long, Integer>) session.getAttribute("cart");

        // Redirect to cart page if the cart is empty or null
        if (customer == null || cart == null || cart.isEmpty()) {
            response.sendRedirect("cart");
            return;
        }

        List<OrderItem> orderItems = new ArrayList<>();
        double totalPrice = 0;

        // Create order items and calculate total price
        for (Map.Entry<Long, Integer> entry : cart.entrySet()) {
            String productId = getServletInfo();
            int quantity = entry.getValue();

            Product product = productDAO.getProductById(productId);
            totalPrice += Double.parseDouble(product.getPrice()) * quantity;

            OrderItem orderItem = new OrderItem(null, product, quantity);
            orderItems.add(orderItem);
        }

        // Create the order and save it to the database
        Order order = new Order(customer, orderItems, totalPrice, "Pending");
        orderDAO.saveOrder(order);

        // Clear the cart after checkout
        session.removeAttribute("cart");

        // Redirect to order confirmation or order history
        response.sendRedirect("orderHistory");
    }
}
