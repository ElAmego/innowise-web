<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Main</title>
</head>
<body>
    <h2>Welcome ${sessionScope.login}!</h2>

    <a href="../controller?command=all_users">All Users</a>

    <form action="../controller" method="POST">
        <input type="hidden" name="command" value="logout"/>
        <button type="submit">Logout</button>
    </form>
</body>
</html>
