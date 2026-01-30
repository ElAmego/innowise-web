<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/login.css">
</head>
<body>
    <div class="login-container">
        <div class="login-card">
            <div class="login-header">
                <h1>Login</h1>
                <p class="login-subtitle">Sign in to your account</p>
            </div>

            <form action="../controller" method="POST" class="login-form">
                <input type="hidden" name="command" value="login"/>

                <div class="form-group">
                    <label>Login:</label>
                    <input type="text" name="login" value="" class="login-input" placeholder="Enter your login" required>
                </div>

                <div class="form-group">
                    <label>Password:</label>
                    <input type="password" name="password" value="" class="login-input" placeholder="Enter your password" required>
                </div>

                <input type="submit" name="sub" value="Login" class="login-btn btn-primary">

                <% if (request.getAttribute("login_error_msg") != null) { %>
                    <div class="error-msg">
                        <%= request.getAttribute("login_error_msg") %>
                    </div>
                <% } %>
            </form>

            <div class="login-footer">
                <a href="../index.jsp" class="login-btn btn-secondary">Back to Home</a>
                <p>Don't have an account? <a href="<%= request.getContextPath() %>/pages/registration.jsp" class="login-link">Register here</a></p>
            </div>
        </div>
    </div>
</body>
</html>