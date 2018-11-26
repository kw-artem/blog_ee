<%@ page import="git.kwartem.blog.Post" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Main</title>
</head>
<body>
    <a href="/new-post">Create post</a>
    <h1>All Posts:</h1>
    <ul>
        <c:forEach var="post" items="${posts}">
            <li><c:out value="${post}"/></li>
        </c:forEach>
    </ul>
</body>
</html>
