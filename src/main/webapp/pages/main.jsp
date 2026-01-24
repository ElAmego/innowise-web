<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<html>
<head>
    <title>Main</title>
</head>
<body>
     <%
        if (session.getAttribute("login") == null) {
            response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
            return;
        }
    %>

    <h2>User Information</h2>
    <p><strong>Login:</strong> ${sessionScope.login}</p>
    <p><strong>Role:</strong> ${sessionScope.user_role}</p>
</body>
</html>
