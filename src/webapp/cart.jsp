<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Your Cart</title>
    <style>
        .container {
            width: 80%;
            margin: 50px auto;
        }

        .cart-item {
            border: 1px solid #ccc;
            padding: 20px;
            margin: 10px 0;
            border-radius: 5px;
        }

        .cart-item-title {
            font-weight: bold;
            font-size: 18px;
        }

        .cart-item-price {
            color: green;
            font-weight: bold;
        }

        .checkout-button {
            background-color: #4CAF50;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Your Cart</h1>

        <c:if test="${cart != null && !cart.isEmpty()}">
            <c:forEach var="entry" items="${cart}">
                <div class="cart-item">
                    <div class="cart-item-title">Product ID: ${entry.key}</div>
                    <div>Quantity: ${entry.value}</div>
                </div>
            </c:forEach>

            <!-- Checkout Button -->
            <form action="checkout" method="post">
                <input type="submit" value="Checkout" class="checkout-button" />
            </form>
        </c:if>

        <c:if test="${cart == null || cart.isEmpty()}">
            <p>Your cart is empty.</p>
        </c:if>

        <a href="products">Continue Shopping</a>
    </div>
</body>
</html>
