<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Available Products</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 80%;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }

        h1, h2 {
            text-align: center;
            color: #333;
        }

        .profile-actions {
            margin-bottom: 30px;
            display: flex;
            flex-direction: column;
            align-items: flex-start;
        }

        .button {
            display: inline-block;
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
            cursor: pointer;
            margin-bottom: 10px;
            width: 200px;
        }

        .button:hover {
            background-color: #45a049;
        }

        .logout-button {
            background-color: #e74c3c;
        }

        .logout-button:hover {
            background-color: #c0392b;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            text-align: left;
            padding: 12px;
        }

        th {
            background-color: #f2f2f2;
            font-weight: bold;
            color: #333;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        .product-name {
            font-weight: bold;
            color: #333;
        }

        .product-description {
            color: #666;
        }

        .product-price {
            color: green;
            font-weight: bold;
        }

        .quantity-input {
            width: 60px;
            padding: 5px;
            margin-left: 5px;
        }

        .add-to-cart-button {
            background-color: #3498db;
            color: white;
            padding: 8px 12px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 5px;
        }

        .add-to-cart-button:hover {
            background-color: #2980b9;
        }

        .view-cart-button {
            background-color: #f39c12;
        }

        .view-cart-button:hover {
            background-color: #e67e22;
        }

        a {
            color: #3498db;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

    <div class="container">
        <h1>Available Products</h1>

        <!-- Profile Actions: View Cart, Edit Profile, Logout -->
        <div class="profile-actions">
            <a href="cart" class="button view-cart-button">View Cart</a>
            <a href="orderHistory" class="button">View Order History</a>
            <a href="editProfile.jsp" class="button">Edit Profile</a>
            <form action="logout" method="post" style="display:inline;">
                <input type="submit" value="Logout" class="button logout-button" />
            </form>
        </div>

        <h2>Products</h2>

        <!-- Products Table -->
        <table>
            <thead>
                <tr>
                    <th>Product ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${availableProducts}">
                    <tr>
                        <td>${product.productID}</td>
                        <td class="product-name">${product.name}</td>
                        <td class="product-description">${product.description}</td>
                        <td class="product-price">${product.price}</td>
                        <td>
                            <!-- Quantity Input -->
                            <form action="cart" method="post" style="display:inline;">
                                <input type="hidden" name="productId" value="${product.productID}" />
                                <input type="number" name="quantity" value="1" min="1" class="quantity-input" required />
                        </td>
                        <td>
                                <!-- Add to Cart Button -->
                                <input type="submit" value="Add to Cart" class="add-to-cart-button" />
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Option to continue shopping -->
        <div style="text-align: center; margin-top: 20px;">
            <a href="products" class="button">Continue Shopping</a>
        </div>
    </div>

</body>
</html>
