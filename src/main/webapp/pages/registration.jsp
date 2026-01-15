<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>Registration</title>
</head>
<body>
<h1> Registration
</h1>
<br/>
<form action="../controller">
    <input type="hidden" name="command" value="registration"/>
    Login: <input type="text" name="login" value=""/>
    <br/>
    Password: <input type="password" name="password" value=""/>
    <br/>
    <input type="submit" name="sub" value="Register"/>
    <br/>
    ${registration_error_msg}
</form>
</body>
</html>