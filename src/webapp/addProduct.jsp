<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Product</title>
    <style>
        .container {
            width: 50%;
            margin: 50px auto;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            font-weight: bold;
            display: block;
        }

        input, textarea {
            width: 100%;
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .submit-button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .submit-button:hover {
            background-color: #45a049;
        }

        .back-button {
            background-color: #e74c3c;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .back-button:hover {
            background-color: #c0392b;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Add New Product</h1>
        <form action="addProduct" method="post">
        <div class="form-group">
                <label for="productID">ProductID:</label>
                <input type="text" id="productID" name="productID" required>
            </div> 
            
            <div class="form-group">
                <label for="name">Product Name:</label>
                <input type="text" id="name" name="name" >
            </div>

            <div class="form-group">
                <label for="description">Product Description:</label>
                <textarea id="description" name="description" ></textarea>
            </div>

            <div class="form-group">
                <label for="price">Price:</label>
                <input  id="price" name="price"  >
            </div>

            <div class="form-group">
                <input type="submit" value="Add Product" class="submit-button">
                <a href="products"><button type="button" class="back-button">Back to Dashboard</button></a>
            </div>
        </form>
    </div>
</body>
</html>
