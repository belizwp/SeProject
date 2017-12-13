<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<head>
    <meta charset="UTF-8">
    <title>Login</title>

    <link rel="stylesheet" href="foundation.css">
    <link rel="stylesheet" href="style.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style type="text/css">
        body{
            display: flex;
            width: 100%;
            height: 100%;
            background:url('picture/login.jpg') 100% no-repeat;
            background-size: cover;
            background-position: center top;
            font-family: 'Kanit', sans-serif;
        }
        .text_input {
            margin-bottom: -10px;
        }
        [type='submit']:hover{
            background-color: #535353;

        }
        button:hover{
            background-color: #535353;
        }
        .login:hover{
            background-color: #535353;
        }

    </style>
</head>
<body>
<div class="show_body">
    <jsp:include page="/templates/header.html"/>
    <c:set var="user" value="${sessionScope.user}"/>
    <c:choose>
        <c:when test="${user != null}">
            <div>    you already logged in.</div>
        </c:when>
        <c:otherwise>
            <form action="/login" method="post">
                <div style="margin-top: 8%;">
                    <br>
                    <br>
                    <center>
                        <input name="username" type="text" class="text_input" placeholder="Username"><br><br>
                        <input name="password" type="password" class="text_input" placeholder="Password"><br><br>
                        <input type="submit" class="login" value="Login" style="margin-bottom: 5px; border-color: #ffffff00; ">
                        <input type="button" class="login" value="Sign up" style="background-color: #888888; border-color: #ffffff00;" onclick="location.href='Register.jsp'">
                    </center>
                </div>
                <jsp:include page="templates/footer.jsp"/>
            </form>
        </c:otherwise>
    </c:choose>
</body>
</html>