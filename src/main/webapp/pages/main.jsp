<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard | User Portal</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
    <div class="main-container">
        <div class="main-card">
            <div class="main-header">
                <h1>User Dashboard</h1>
                <div class="welcome-message">
                    Welcome ${sessionScope.login}!
                </div>
                <p class="subtitle">Manage your account and explore platform features</p>
            </div>

            <div class="actions-container">
                <div class="action-section">
                    <h3>User Management</h3>
                    <div class="actions-buttons">
                        <a href="../controller?command=all_users" class="main-btn btn-primary">
                            View All Users
                        </a>
                    </div>
                </div>

                <div class="action-section">
                    <h3>Account Actions</h3>
                    <div class="actions-buttons">
                        <form action="../controller" method="POST" class="action-form">
                            <input type="hidden" name="command" value="delete"/>
                            <button type="submit" class="main-btn btn-danger">
                                Delete Account
                            </button>
                        </form>

                        <form action="../controller" method="POST" class="action-form">
                            <input type="hidden" name="command" value="logout"/>
                            <button type="submit" class="main-btn btn-logout">
                                Logout
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>