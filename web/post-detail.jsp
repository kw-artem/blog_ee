<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Post detail</title>
</head>
<body>
<h1>
    Title: <c:out value="${post.title}"/>
</h1>
---------------------------------------------------------------
<p>
    <c:out value="${post.content}"/>
</p>
------------------------------
<p>
    Author:
    <i>
        <c:out value="${post.author}"/>
    </i>
</p>
<p>
    Tags:
    <i>
        <c:out value="${post.tags}"/>
    </i>
</p>
---------------------------------------------------------------
<h3>
    Comments:
</h3>
<% int i = 1;%>
<c:forEach var="entry" items="${post.comments}">
    ~<c:out value="${i}"/>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    <div>
        <p>
            <c:out value="${entry.content}"/>
        </p>
        <i><c:out value="${entry.author_login}"/></i> - <c:out value="${entry.author_email}"/>
    </div>
</c:forEach>

</body>
</html>
