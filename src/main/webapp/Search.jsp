<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Sheet and Share</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

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
<form action="/search" method="get">
    <section>
        <div id="select-group" align="center">

            <input type="text" class="text_input" name="title" placeholder="File name">

            <select name="faculty" id="faculty-list" onchange="getDepartment(this.value)">
                <option selected="" disabled="">เลือกคณะ</option>
                <option value=0>--ทั้งหมด--</option>
                <sql:query dataSource="${dataSource}" var="faculty_rows">
                    SELECT * from `faculty`;
                </sql:query>
                <c:forEach var="faculty" items="${faculty_rows.rows}">
                    <option value='${faculty.id}'>${faculty.name}</option>
                </c:forEach>
            </select>

            <select name="department" id="department-list" onchange="getBranch(this.value)">
                <option selected="" disabled="">เลือกภาควิชา</option>
                <option value="0">--ทั้งหมด--</option>

            </select>

            <select name="branch" id="branch-list" onchange="getSubject(this.value)">
                <option selected="" disabled="">เลือกสาขาวิชา</option>
                <option value="0">--ทั้งหมด--</option>

            </select>

            <select name="year" id="year-list" onchange="getSubjectWithYear(this.value)">
                <option selected="" disabled="">เลือกชั้นปี</option>
                <option value="0">--ทั้งหมด--</option>

            </select>

            <select name="subject" id="subject-list" onchange="">
                <option selected="" disabled="">วิชา</option>

            </select>

        <a href="subject.html"><img src="picture/icon_search.svg" id="icon-search" onclick=""></a>
        </div>
    </section><jsp:include page="templates/header.jsp"/>
</form>
</body>
</html>

