<html>
<head>
    <title>Register</title>
    <style>
        /* Styling the form container */
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
        input[type="password"],
        input[type="number"],
        textarea,
        select {
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

        .login-link {
            text-align: center;
            margin-top: 10px;
        }

        .login-link a {
            color: #007BFF;
            text-decoration: none;
        }

        .login-link a:hover {
            text-decoration: underline;
        }
    </style>
    <script>
        function validateEmail(email) {
            const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            return re.test(String(email).toLowerCase());
        }

        function validateForm() {
            const email = document.getElementById("email").value;
            const errorMsg = document.getElementById("emailError");
            const password = document.getElementById("password").value;

            // Email validation
            if (!validateEmail(email)) {
                errorMsg.innerHTML = "Invalid email format.";
                return false;
            }

            // Password validation
            if (password.length < 6) {
                document.getElementById("passwordError").innerHTML = "Password must be at least 6 characters long.";
                return false;
            }

            errorMsg.innerHTML = "";
            document.getElementById("passwordError").innerHTML = "";
            return true; // Allow form submission if validation is successful
        }
    </script>
</head>
<body>
    <div class="form-container">
        <h1>Welcome!</h1>
        <form action="customers" method="post" onsubmit="return validateForm()">
            <input type="hidden" name="action" value="register" />
            
            <label for="name">Name:</label>
            <input type="text" name="name" required />
            
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required />
            <span id="emailError" class="error-message"></span>

            <label for="age">Age:</label>
            <input type="number" name="age" min="0" required />

            <label for="life_activity">Life Activity:</label>
            <select name="life_activity" required>
                <option value="studies">Studies</option>
                <option value="work">Work</option>
            </select>

            <label for="address">Address:</label>
            <textarea name="address" required></textarea>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required />
            <span id="passwordError" class="error-message"></span>

            <input type="submit" value="Register" />
        </form>
        <div class="login-link">
            <p>Already registered? <a href="customers?action=login">Login here</a></p>
        </div>
    </div>
</body>
</html>
