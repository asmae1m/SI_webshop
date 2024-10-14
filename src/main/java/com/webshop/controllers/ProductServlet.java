package com.webshop.controllers;

import com.webshop.dao.ProductDAO;
import com.webshop.models.Customer;
import com.webshop.models.Product;
import com.webshop.models.Role;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/products", "/addProduct", "/deleteProduct"})
public class ProductServlet extends HttpServlet {

    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        productDAO = new ProductDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        fetchAndPersistProductsFromAPI();

        List<Product> availableProducts = productDAO.getProducts();  // Get from DB, not API

        request.setAttribute("availableProducts", availableProducts);

        if (customer.getRole() == Role.ADMIN) {
            // If admin, forward to admin dashboard
            RequestDispatcher dispatcher = request.getRequestDispatcher("adminDashboard.jsp");
            dispatcher.forward(request, response);
        } else {
            // If regular user, forward to the regular products page
            RequestDispatcher dispatcher = request.getRequestDispatcher("products.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();

        if ("/addProduct".equals(servletPath)) {
            // Add new product
            String productID = request.getParameter("productID");         
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String price = request.getParameter("price");

            Product product = new Product(productID, name, description, price);
            productDAO.saveProduct(product);

            response.sendRedirect("products");

        } else if ("/deleteProduct".equals(servletPath)) {
            Long productId = Long.parseLong(request.getParameter("productId"));
            productDAO.deleteProduct(productId);

            response.sendRedirect("products");
        }
    }

    private void fetchAndPersistProductsFromAPI() throws IOException {
        String username = "service-user";  
        String password = "service-user"; 

        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String(encodedAuth, StandardCharsets.UTF_8);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://localhost:4004/rest/api/getProducts");

        request.addHeader("Authorization", authHeader);

        HttpResponse response = httpClient.execute(request);

        if (response.getStatusLine().getStatusCode() == 200) {
            Reader reader = new InputStreamReader(response.getEntity().getContent());

            CsvToBean<Product> csvToBean = new CsvToBeanBuilder<Product>(reader)
                .withType(Product.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withThrowExceptions(false) 
                .build();

            List<Product> products = csvToBean.stream()
                .map(p -> new Product(p.getProductID(), p.getName(), p.getDescription(), p.getPrice())) 
                .collect(Collectors.toList());

            List<CsvException> exceptions = csvToBean.getCapturedExceptions();
            for (CsvException e : exceptions) {
                // Log or handle exceptions as needed
                System.err.println("Error parsing CSV line: " + e.getLine());
                e.printStackTrace();
            }

            reader.close();

            for (Product product : products) {
                Product existingProduct = productDAO.getProductById(product.getProductID());
                if (existingProduct == null) {
                    productDAO.saveProduct(product);  
                }
            } 
        } else {
            throw new IOException("Failed to fetch products: " + response.getStatusLine().getReasonPhrase());
        }
    }
}
