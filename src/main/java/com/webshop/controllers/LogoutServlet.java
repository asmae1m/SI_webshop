package com.webshop.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Invalidate the session if one exists
        HttpSession session = request.getSession(false); // Get session, don't create if it doesn't exist
        if (session != null) {
            session.invalidate(); // Invalidate the session
        }

        // Redirect to the login page after logging out
        response.sendRedirect("login.jsp");
    }
}
