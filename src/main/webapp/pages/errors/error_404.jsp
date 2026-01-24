<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Page Not Found | Error 404</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/error.css">
</head>
<body>
    <div class="error-container">
        <div class="error-card">
            <div class="error-header">
                <div class="error-code">404</div>
                <h1 class="error-title">Page Not Found</h1>
                <p class="error-subtitle">The page you're looking for doesn't exist</p>
            </div>

            <div class="error-icon">🔍</div>

            <div class="error-message-box">
                <p>We couldn't find the page you were looking for. This might be because:</p>
                <p>The page has been moved, deleted, or you entered an incorrect URL.</p>
            </div>

            <a href="<%= request.getContextPath() %>/pages/main.jsp" class="error-btn btn-primary">
                Go to Home Page
            </a>
        </div>
    </div>
</body>
</html>