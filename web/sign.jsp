<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 24.11.2018
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>BLOG</title>
</head>
<body>
<h1>Sign up</h1>

<form action='/sign' method="post">
    <p>Login: <input type="text" name="login" /></p>
    <p>Password: <input type="password" name="password" /></p>
    <p>Email: <input type="email" name="email"></p>
    <input type="submit" value="Submit" />
</form>

</body>
</html>
