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
    <h1>Hello</h1>
    <%
      java.util.Date date = new java.util.Date();
    %>
    Now is <%=date.toString()%>
    <p>
      <p>Login: <%= request.getParameter("login") %></p>
      <p>Password: <%= request.getParameter("password") %></p>
      <p>Email: <%= request.getParameter("email") %></p>
      <p>Message: <%= request.getParameter("message") %></p>
    </p>
  </body>
</html>
