<%@ page isErrorPage = "True" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Server Error | Error 500</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/error.css">
</head>
<body>
    <div class="error-container">
        <div class="error-card">
            <div class="error-header">
                <div class="error-code">500</div>
                <h1 class="error-title">Internal Server Error</h1>
                <p class="error-subtitle">Something went wrong on our end</p>
            </div>

            <div class="error-icon">⚙️</div>

            <div class="error-message-box">
                <p>We're experiencing technical difficulties. Our team has been notified and is working to fix the issue.</p>
                <p>Please try again in a few moments or contact support if the problem continues.</p>
            </div>

            <a href="<%= request.getContextPath() %>/pages/main.jsp" class="error-btn btn-primary">
                Go to Home Page
            </a>
        </div>
    </div>
</body>
</html>