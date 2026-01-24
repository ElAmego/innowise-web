<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cafe | Welcome</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/index.css">
</head>
<body>
    <div class="container">
        <div class="card">
            <div class="welcome-header">
                <h1>Welcome!</h1>
                <p class="subtitle">Secure access to your personal dashboard</p>
            </div>

            <div class="message-box">
                <p>To access your personalized dashboard and all platform features, you need to authenticate.</p>
                <p>Please choose one of the following options:</p>
            </div>

            <div class="options-container">
                <div class="option-card">
                    <h2>New User?</h2>
                    <p>Create an account to get started with our services</p>
                    <a href="pages/registration.jsp" class="btn btn-primary">Create Account</a>
                </div>

                <div class="option-card">
                    <h2>Existing User?</h2>
                    <p>Sign in to access your account and continue</p>
                    <a href="pages/login.jsp" class="btn btn-secondary">Sign In</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>