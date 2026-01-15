<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
</head>
<body>
<h1> Login
</h1>
<br/>
<form action="../controller">
    <input type="hidden" name="command" value="login"/>
    Login: <input type="text" name="login" value=""/>
    <br/>
    Password: <input type="password" name="password" value=""/>
    <br/>
    <input type="submit" name="sub" value="Login"/>
    <br/>
    ${login_error_msg}
</form>
</body>
</html>