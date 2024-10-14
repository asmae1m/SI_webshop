<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Order History</title>
    <style>
        .order-history-container {
            width: 80%;
            margin: 50px auto;
        }

        .order {
            border: 1px solid #ccc;
            padding: 20px;
            margin: 20px 0;
            border-radius: 5px;
        }

        .order h3 {
            margin-top: 0;
        }

        .order-items ul {
            list-style-type: none;
            padding-left: 0;
        }

        .order-items li {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="order-history-container">
        <h1>Your Order History</h1>

        <c:if test="${orders != null && !orders.isEmpty()}">
            <c:forEach var="order" items="${orders}">
                <div class="order">
                    <h3>Order ID: ${order.id}</h3>
                    <p>Total Price: $${order.totalPrice}</p>
                    <p>Status: ${order.status}</p>

                    <h4>Items:</h4>
                    <div class="order-items">
                        <ul>
                            <c:forEach var="item" items="${order.orderItems}">
                                <li>${item.product.name} - Quantity: ${item.quantity}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </c:forEach>
        </c:if>

        <c:if test="${orders == null || orders.isEmpty()}">
            <p>You have not placed any orders yet.</p>
        </c:if>

        <a href="products">Continue Shopping</a>
    </div>
</body>
</html>