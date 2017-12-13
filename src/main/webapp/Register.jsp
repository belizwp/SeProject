<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Sheet and Share</title>

    <link rel="stylesheet" href="foundation.css">
    <link rel="stylesheet" href="style.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style type="text/css">
        body{
            display: flex;
            width: 100%;
            height: 100%;
            background:url('picture/regis.jpeg') 100% no-repeat;
            background-size: cover;
            background-position: center top;
        }
        p{
            margin-top: 7px;
            margin-left: 40%;
        }

    </style>
</head>
<body>

<div class="show_body">
    <jsp:include page="templates/header.html"/>
    <form action="/register">
        <center style="margin-top: 150px;">
            <input type="text" class="text_input" name="fname" placeholder="ชื่อ"/>
            <input type="text" class="text_input" name="lname" placeholder="นามสกุล"/>
            <input type="text" class="text_input" name="username" placeholder="Username"/>
            <input type="password" class="text_input" name="password" placeholder="Password"/>
            <input type="password" class="text_input" name="password" placeholder="Re-Password"/>
            <input type="text" class="text_input" name="email" placeholder="E-mail"/>

            <input type="submit" class="btn" value="Sign up">
        </center>
    </form>

</body>
</html>
