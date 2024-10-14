<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <style>
        .form-container {
            width: 400px;
            margin: 50px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
            background-color: #f9f9f9;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        label {
            font-size: 16px;
            color: #333;
            display: block;
            margin-bottom: 5px;
        }

        input[type="email"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            font-size: 14px;
        }

        input[type="submit"] {
            width: 100%;
            background-color: #4CAF50;
            color: white;
            padding: 10px;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        .error-message {
            color: red;
            font-size: 12px;
        }

        .register-link {
            text-align: center;
            margin-top: 10px;
        }

        .register-link a {
            color: #007BFF;
            text-decoration: none;
        }

        .register-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h1>Login</h1>
        <form action="customers" method="post">
            <input type="hidden" name="action" value="login" />

            <label for="email">Email:</label>
            <input type="email" name="email" required />

            <label for="password">Password:</label>
            <input type="password" name="password" required />

            <input type="submit" value="Login" />

            <c:if test="${not empty message}">
                <div class="error-message">${message}</div>
            </c:if>
        </form>

        <div class="register-link">
            <p>Don't have an account? <a href="customers?action=register">Register here</a></p>
        </div>
    </div>
</body>
</html>
