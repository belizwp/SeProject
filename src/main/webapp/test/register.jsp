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
            background:url('/picture/bg1366-768.jpg') 100% no-repeat;
            background-size: cover;
            background-position: center top;
        }
        p{
            margin-top: 7px;
            margin-left: 40%;
        }
        .text_input {
            margin-left: -35%;
        }
    </style>
</head>
<body>
<div id="nav-bar">
    <ul class="menu medium-expanded nav">
        <li id="logo"><a href="Search.jsp">Register</a></li>
    </ul>
    <form action="/register">
        <center>
            <input type="text" class="text_input" name="fname" placeholder="First name">
            <input type="text" class="text_input" name="lname" placeholder="Last name">
            <input type="text" class="text_input" name="username" placeholder="Username">
            <input type="password" class="text_input" name="password" placeholder="Password">
            <input type="password" class="text_input" name="confirmpass" placeholder="Comfirm Password">
            <input type="text" class="text_input" name="email" placeholder="Email">
            <input type="text" class="text_input" name="faculty" placeholder="Faculty">
            <input type="text" class="text_input" name="branch" placeholder="Branch">
            <input type="text" class="text_input" name="department" placeholder="Department">

            <input type="submit" class="btn" value="Sign up"></center>

    </form>

</div>

</body>
</html>
