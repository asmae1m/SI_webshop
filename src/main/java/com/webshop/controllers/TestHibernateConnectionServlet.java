package com.webshop.controllers;

import com.webshop.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/testConnection")
public class TestHibernateConnectionServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Open a Hibernate session
        Session session = null;
        Transaction transaction = null;
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            // If session is opened, the connection is successful
            response.getWriter().println("Connection to the database was successful!");

            transaction.commit();  // Commit the transaction
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            response.getWriter().println("Failed to establish a connection: " + e.getMessage());
        } finally {
            if (session != null) session.close();  // Close the session
        }
    }
}
