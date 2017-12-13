<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <jsp:include page="/templates/header.jsp"/>
    <c:set var="user" value="${sessionScope.user}"/>
    <c:choose>
        <c:when test="${user != null}">
            <div>    you already logged in.</div>
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
                <jsp:include page="templates/footer.jsp"/>
            </form>
        </c:otherwise>
    </c:choose>
</body>
</html>