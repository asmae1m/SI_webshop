<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        .container {
            width: 80%;
            margin: 50px auto;
        }

        .admin-actions {
            margin-bottom: 30px;
        }

        .product {
            border: 1px solid #ccc;
            padding: 20px;
            margin: 10px 0;
            border-radius: 5px;
        }

        .product-title {
            font-weight: bold;
            font-size: 18px;
        }

        .product-description {
            margin: 5px 0;
        }

        .product-price {
            color: green;
            font-weight: bold;
        }

        .admin-button {
            background-color: #4CAF50;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-right: 10px;
        }

        .admin-button:hover {
            background-color: #45a049;
        }

        .delete-button {
            background-color: #e74c3c;
        }

        .delete-button:hover {
            background-color: #c0392b;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Admin Dashboard</h1>

        <!-- Link to Add Product Page -->
        <div class="admin-actions">
            <a href="addProduct.jsp" class="admin-button">Add New Product</a>
        </div>

        <h2>Available Products</h2>

        <c:forEach var="product" items="${availableProducts}">
            <div class="product">
                <div class="product-title">${product.name}</div>
                <div class="product-description">${product.description}</div>
                <div class="product-price">Price: ${product.price}</div>

                <!-- Delete button for each product -->
                <form action="deleteProduct" method="post" style="display:inline;">
                    <input type="hidden" name="productId" value="${product.productID}" />
                    <input type="submit" value="Delete" class="admin-button delete-button" />
                </form>
            </div>
        </c:forEach>

        <!-- Logout button for the admin -->
        <form action="logout" method="post">
            <input type="submit" value="Logout" class="admin-button delete-button">
        </form>
    </div>
</body>
</html>
