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
            background:url('picture/bg1600-900.jpg') 100% no-repeat;
            background-size: cover;
            background-position: center top;
        }
    </style>
</head>
<body>
<div id="nav-bar">
    <ul class="menu medium-expanded nav">
        <li><a href="/Search.jsp">Search</a></li>
        <li><a href="">เอกสารที่ถูกใจ</a></li>
        <li id="logo"><a href="/Search.jsp">Sheet&Share </a></li>
        <li><a href="">เอกสารที่แบ่งปัน</a></li>
        <li><a href="/Profile.jsp">Profile</a></li>
    </ul>
</div>

<section>
    <div id="select-group" align="center">
        <select>
            <option selected="" disabled="">ปีการศึกษา</option>
            <option value="saab">Saab</option>
            <option value="opel">Opel</option>
            <option value="audi">Audi</option>
        </select>
        <select>
            <option selected="" disabled="">ภาคเรียน</option>
            <option value="saab">Saab</option>
            <option value="opel">Opel</option>
            <option value="audi">Audi</option>
        </select>
        <select>
            <option selected="" disabled="">เลือกคณะ</option>
            <option value="saab">Saab</option>
            <option value="opel">Opel</option>
            <option value="audi">Audi</option>
        </select>
        <select>
            <option selected="" disabled="">เลือกภาควิชา</option>
            <option value="saab">Saab</option>
            <option value="opel">Opel</option>
            <option value="audi">Audi</option>
        </select>
        <select>
            <option selected="" disabled="">เลือกสาขาวิชา</option>
            <option value="saab">Saab</option>
            <option value="opel">Opel</option>
            <option value="audi">Audi</option>
        </select>
        <select>
            <option selected="" disabled="">เลือกชั้นปี</option>
            <option value="saab">Saab</option>
            <option value="opel">Opel</option>
            <option value="audi">Audi</option>
        </select>

        <a href="subject.html"><img src="picture/icon_search.svg" id="icon-search" onclick=""></a>
        <jsp:include page="templates/header.jsp"/>

</body>
</html>

