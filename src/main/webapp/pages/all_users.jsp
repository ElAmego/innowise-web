<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Management | All Users</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/all_users.css">
</head>
<body>
    <div class="users-container">
        <div class="users-card">
            <div class="users-header">
                <h1>User Management</h1>
                <p class="users-subtitle">Complete list of registered users</p>
            </div>

            <div class="users-stats">
                <div class="stats-count">${users != null ? users.size() : 0}</div>
                <div class="stats-label">Total Registered Users</div>
            </div>

            <c:choose>
                <c:when test="${not empty users}">
                    <div class="users-table-container">
                        <table class="users-table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Username</th>
                                    <th>Phone Number</th>
                                    <th>Loyalty Points</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="user" items="${users}">
                                    <tr>
                                        <td>${user.userId}</td>
                                        <td>${user.login}</td>
                                        <td class="phone-cell">
                                            ${user.phoneNumber != null ? user.phoneNumber : 'N/A'}
                                        </td>
                                        <td>
                                            <span class="loyalty-badge">${user.loyaltyPoints} points</span>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="empty-state">
                        <div class="empty-icon">👥</div>
                        <p class="empty-message">No users found in database</p>
                        <p class="empty-submessage">Start by registering new users through the registration page</p>
                    </div>
                </c:otherwise>
            </c:choose>

            <div class="users-footer">
                <a href="${pageContext.request.contextPath}/pages/main.jsp" class="users-btn btn-primary">
                    Back to Main
                </a>
            </div>
        </div>
    </div>
</body>
</html>