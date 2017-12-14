<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page isELIgnored="false" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>

    <link rel="stylesheet" href="foundation.css">
    <link rel="stylesheet" href="style.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style type="text/css">
        body{
            display: flex;
            width: 100%;
            height: 100%;
            background:url('picture/cac2.jpg') 100% no-repeat;
            background-size: cover;
            background-position: center top;
            font-family: 'Kanit', sans-serif;
        }

    </style>
</head>
<body>

<c:set var="user" value="${sessionScope.user}"/>
<c:choose>
    <c:when test="${user != null}">
        <div class="show_body">
            <jsp:include page="navbar.html"/>
            <div class="profile">
                <ul class="menu ">
                    <li><img src="picture/man.svg" id="profile_picture" style="margin: 30px;"></li>
                    <li><label>${user.username}</label>
                        <label>${user.fname} ${user.lname}</label>
                        <label>${user.email}</label>
                    </li>
                </ul>
            </div>

            <center>
                <h5 style="margin-top: 35px;">รายการที่อัพโหลด</h5>
                <table style="width: 65%; margin-top: 20px;">
                    <thead>
                    <th style="width: 50%;"><center>ชื่อไฟล์</center></th>
                    <th style="width: 40%;"><center>วิชา</center></th>
                    <th> </th>

                    </thead>

                    <sql:query dataSource="${dataSource}" var="sheetMe_rows">
                        SELECT * FROM `sheet` WHERE create_by = '${user.username}';
                    </sql:query>

                    <c:forEach var="sheet" items="${sheetMe_rows.rows}">
                    <tr>
                        <td><center>${sheet.title}</center></td>
                        <td>
                            <center>${sheet.create_by}</center>
                        </td>
                        <td><center><a href="/delete?id=${sheet.id}"><img class="del" src="picture/delete.svg"></a></center></td>
                    </tr>
                    </c:forEach>
                </table>
                <input type="button" class="btn" value="Logout" onclick="location.href='/logout'">
            </center>
        </div>

    </c:when>
    <c:otherwise>
        <jsp:include page="/Login.jsp"/>
    </c:otherwise>
</c:choose>

</body>
</html>
