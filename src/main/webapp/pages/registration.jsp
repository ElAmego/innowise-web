<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cafe | Registration</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/registration.css">
</head>
<body>
    <div class="reg-container">
        <div class="reg-card">
            <div class="reg-header">
                <h1>Registration</h1>
                <p class="reg-subtitle">Create your account</p>
            </div>

                <form action="../controller" method="POST" class="reg-form">
                <input type="hidden" name="command" value="registration"/>

                <div class="form-group">
                    <label>Login:</label>
                    <input type="text" name="login" value="" class="reg-input" placeholder="Enter your login" required>
                </div>

                <div class="form-group">
                    <label>Password:</label>
                    <input type="password" name="password" value="" class="reg-input" placeholder="Enter your password" required>
                </div>

                <input type="submit" name="sub" value="Register" class="reg-btn btn-primary">

                <% if (request.getAttribute("registration_error_msg") != null) { %>
                    <div class="error-msg">
                        <%= request.getAttribute("registration_error_msg") %>
                    </div>
                <% } %>
            </form>

            <div class="reg-footer">
                <a href="../index.jsp" class="reg-btn btn-secondary">Back to Home</a>
                <p>Already have an account? <a href="<%= request.getContextPath() %>/pages/login.jsp" class="reg-link">Login here</a></p>
            </div>
        </div>
    </div>
</body>
</html>