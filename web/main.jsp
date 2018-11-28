<%@ page import="git.kwartem.blog.Post" %>
<%@ page import="git.kwartem.blog.User" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>Main</title>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<a href="/login/logout">Logout</a>
<a href="/new-post">Create post</a>
<h1>All Posts:</h1>
    <c:forEach var="post" items="${posts}">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">
                    <a href="/post?id=<c:out value="${post.id}"/>">
                        <c:out value="${post.title}"/>
                    </a>
                </h5>
                <p class="card-text"><c:out value="${post.content}"/></p>
                <footer class="blockquote-footer"><a href="#"><c:out value="${post.author}"/></a></footer>
            </div>
        </div>
    </c:forEach>
</body>
</html>
