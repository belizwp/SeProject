<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
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
        <li><a href="/Search.jsp">ค้นหา</a></li>
        <li><a href="">เอกสารที่ถูกใจ</a></li>
        <li id="logo"><a href="/Search.jsp">Sheet&Share </a></li>
        <li><a href="">เอกสารที่แบ่งปัน</a></li>
        <li><a href="/Profile.jsp">โปรไฟล์</a></li>
    </ul>
</div>
<form action="/search" method="get">
    <section>
        <div id="select-group" align="center">

            <input type="text" class="text_input" name="title" placeholder="File name">

            <select name="semester" id="semester-list" onchange="getSubjectWithSemester(this.value)">
                <option selected="" disabled="">ภาคเรียน</option>
                <option value=0>--ทั้งหมด--</option>
                <option value=1>1</option>
                <option value=2>2</option>

            </select>

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
                <option value=0>--ทั้งหมด--</option>

            </select>

            <select name="branch" id="branch-list" onchange="getSubject(this.value)">
                <option selected="" disabled="">เลือกสาขาวิชา</option>
                <option value=0>--ทั้งหมด--</option>

            </select>

            <select name="year" id="year-list" onchange="getSubjectWithYear(this.value)">
                <option selected="" disabled="">เลือกชั้นปี</option>
                <option value=0>--ทั้งหมด--</option>

            </select>

            <select name="subject" id="subject-list" onchange="">
                <option selected="" disabled="">วิชา</option>
                <option value=0>--ทั้งหมด--</option>

            </select>

        <a href="subject.html"><img src="picture/icon_search.svg" id="icon-search" onclick=""></a>
        </div>
    </section>
</form>
</body>
<script>
    function clearOption(id) {
        $(id)
            .find('option')
            .remove()
            .end()
            .append("<option value=0>--ทั้งหมด--</option>")
        ;
    }

    function getDepartment(val) {
        if (val == 0) {
            clearOption('#department-list')
            clearOption('#branch-list');
            clearOption('#year-list');
            clearOption('#subject-list');
        } else {
            $.ajax({
                type: "POST",
                url: "/getChoice",
                data: {id: val, type: "department", def: "all"},
                success: function (data) {
                    $("#department-list").html(data);
                    clearOption('#branch-list');
                    clearOption('#year-list');
                    clearOption('#subject-list');
                }
            });
        }
    }

    function getBranch(val) {
        if (val == 0) {
            clearOption('#branch-list');
            clearOption('#year-list');
            clearOption('#subject-list');
        } else {
            $.ajax({
                type: "POST",
                url: "/getChoice",
                data: {id: val, type: "branch", def: "all"},
                success: function (data) {
                    $("#branch-list").html(data);
                    clearOption('#year-list');
                    clearOption('#subject-list');
                }
            });
        }
    }

    function getYearByBranchId(val) {
        if (val == 0) {
            clearOption('#year-list');
            clearOption('#subject-list');
        } else {
            $.ajax({
                type: "POST",
                url: "/getChoice",
                data: {id: val, type: "year", def: "all"},
                success: function (data) {
                    $("#year-list").html(data);
                    clearOption('#subject-list');
                }
            });
        }
    }

    function getSubject(val) {
        getYearByBranchId(val);

        if (val == 0) {
            clearOption('#subject-list');
        } else {
            $.ajax({
                type: "POST",
                url: "/getChoice",
                data: {id: val, type: "subject", semester: $("#semester-list").val(), def: "all"},
                success: function (data) {
                    $("#subject-list").html(data);
                }
            });
        }
    }

    function getSubjectWithYear(year) {
        $.ajax({
            type: "POST",
            url: "/getChoice",
            data: {
                id: $("#branch-list").val(),
                year: year,
                type: "sub-year",
                semester: $("#semester-list").val(),
                def: "all"
            },
            success: function (data) {
                clearOption('#subject-list');
                $("#subject-list").html(data);
            }
        });
    }

    function getSubjectWithSemester(val) {
        $.ajax({
            type: "POST",
            url: "/getChoice",
            data: {
                id: $("#branch-list").val(),
                year: $("#year-list").val(),
                type: "sub-semester",
                semester: val,
                def: "all"
            },
            success: function (data) {
                clearOption('#sub-semester');
                $("#subject-list").html(data);
            }
        });
    }

    function resetFillter() {
        $("#semester-list").val(0);
        $("#faculty-list").val(0);
        clearOption('#department-list')
        clearOption('#branch-list');
        clearOption('#year-list');
        clearOption('#subject-list');
    }
</script>
</html>