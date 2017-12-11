<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<jsp:include page="menu.html"/>
<c:set var="user" value="${sessionScope.user}"/>
<c:choose>
    <c:when test="${user != null}">
        you already logged in.
    </c:when>
    <c:otherwise>
        <form action="/login" method="post">
            Username: <input type="text" name="username"><br>
            Password: <input type="password" name="password"><br>
            <input type="submit" value="Login">
        </form>
    </c:otherwise>
</c:choose>
</body>
</html>