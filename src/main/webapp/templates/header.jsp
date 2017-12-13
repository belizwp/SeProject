<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Header | Search Sheet Please</title>

    <link rel="stylesheet" href="/foundation.css">
    <link rel="stylesheet" href="/style.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style type="text/css">
        body{
            display: flex;
            width: 100%;
            height: 100%;
            background:url('/picture/login.jpg') 100% no-repeat;
            background-size: cover;
            background-position: center top;
            font-family: 'Kanit', sans-serif;
        }

    </style>
</head>
<body>
    <div id="nav-bar">
        <ul class="menu medium-expanded nav">
            <li><a href="/Search.jsp">Search</a></li>
            <li><a href="/Profile.jsp">Profile</a></li>
            <li id="logo"><a href="/search.html">Sheet&Share </a></li>
            <li><a href="/Login.jsp">Login</a></li>
            <li><a href="/logout">Logout</a></li>
        </ul>