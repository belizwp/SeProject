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
<c:set var="fac" value="${sessionScope.fac}"/>
<c:set var="br" value="${sessionScope.br}"/>
<c:set var="dep" value="${sessionScope.dep}"/>
<c:choose>
    <c:when test="${user != null}">
        <br>
        <br>
        <center>
        <table border="1">
            <tbody>
            <tr>
                <td>Username</td>
                <td>${user.username}</td>
            </tr>
            <tr>
                <td>First name</td>
                <td>${user.fname}</td>
            </tr>
            <tr>
                <td>Last name</td>
                <td>${user.lname}</td>
            </tr>
            <tr>
                <td>Email</td>
                <td>${user.email}</td>
            </tr>
            <tr>
                <td>Faculty</td>
                <td>${fac.faculty}</td>
            </tr>
            <tr>
                <td>Branch</td>
                <td>${br.branch}</td>
            </tr>
            <tr>
                <td>Department</td>
                <td>${dep.department}</td>
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
        </table>
        <input type="button" class="btn" value="Logout" onclick="location.href='/logout'"></center>
    </c:when>
    <c:otherwise>
        <jsp:include page="/Login.jsp"/>
    </c:otherwise>
</c:choose>

</body>
</html>
