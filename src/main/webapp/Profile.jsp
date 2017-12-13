<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
</head>
<body>
<jsp:include page="/templates/header.jsp"/>
<c:set var="user" value="${sessionScope.user}"/>
<c:choose>
    <c:when test="${user != null}">
        <center>
        <table border="1">
            <tbody>
            <tr>
                <td>Username</td>
                <td>${user.username}</td>
            </tr>
            <tr>
                <td>Fname</td>
                <td>${user.fname}</td>
            </tr>
            <tr>
                <td>Lname</td>
                <td>${user.lname}</td>
            </tr>
            <tr>
                <td>Email</td>
                <td>${user.email}</td>
            </tr>
            <tr>
                <td>create_time</td>
                <td>${user.createTime}</td>
            </tr>
            <tr>
                <td>update_time</td>
                <td>${user.updateTime}</td>
            </tr>
            </tbody>
        </table></center>
    </c:when>
    <c:otherwise>
        <jsp:include page="/Login.jsp"/>
    </c:otherwise>
</c:choose>
</body>
</html>
