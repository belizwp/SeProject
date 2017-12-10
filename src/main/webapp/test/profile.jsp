<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
</head>
<body>
<a href="register.html">Register</a> | <a href="login.html">Login</a> | <a href="/logout">Logout</a> | <a href="profile.jsp">Profile</a>
<hr>
${sessionScope.user.username}<br>
${sessionScope.user.createTime}<br>
</body>
</html>