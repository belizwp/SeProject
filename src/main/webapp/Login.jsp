<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
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

    </style>
</head>
<body>
<div id="nav-bar">
    <ul class="menu medium-expanded nav">
        <li><a href="search.html">ค้นหา</a></li>
        <li><a href="search.html">เอกสารที่ถูกใจ</a></li>
        <li id="logo"><a href="search.html">Sheet&Share </a></li>
        <li><a href="search.html">เอกสารที่แบ่งปัน</a></li>
        <li><a href="login.html">โปรไฟล์</a></li>
    </ul>
    <c:set var="user" value="${sessionScope.user}"/>
    <c:choose>
        <c:when test="${user != null}">
            you already logged in.
        </c:when>
        <c:otherwise>
            <form action="/login" method="post">
                <div style="margin-top: 8%;">
                    <center>
                        <input name="username" type="text" class="text_input" placeholder="Username"><br>
                        <input name="password" type="password" class="text_input" placeholder="Password"><br>
                        <input type="submit" class="login" value="Login">
                        <div><font color="grey"><a href="login.html">ลืมรหัสผ่าน?</a></font></div>

                        <hr width="20%" align="center" size="50" noshade color="grey">
                        <font color="grey">ลงทะเบียนหรือเข้าสู่ระบบด้วย</font><br>

                        <input type="button" class="btn" value="Sign up" onclick="location.href='register.html'"> <input type="button" class="btn" id="iconfacebook" value="f" onclick="location.href='www.facebook.com'">
                    </center>
                </div>
            </form>
        </c:otherwise>
    </c:choose>

</div>

</body>
</html>
