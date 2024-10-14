<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Profile</title>
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

        input[type="text"],
        input[type="email"],
        textarea {
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

    </style>
</head>
<body>
    <div class="form-container">
        <h1>Edit Profile</h1>
        <form action="updateProfile" method="post">
            <label for="name">Name:</label>
            <input type="text" name="name" value="${customer.name}" required />

            <label for="email">Email:</label>
            <input type="email" name="email" value="${customer.email}" required />

            <label for="address">Address:</label>
            <textarea name="address" required>${customer.address}</textarea>

            <label for="age">Age:</label>
            <input type="number" name="age" value="${customer.age}" min="0" required />

            <input type="submit" value="Update" />

            <c:if test="${not empty message}">
                <div class="error-message">${message}</div>
            </c:if>
        </form>
    </div>
</body>
</html>
