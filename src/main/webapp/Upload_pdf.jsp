<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <link rel="stylesheet" href="foundation.css">
    <link rel="stylesheet" href="style.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style type="text/css">
        body {
            display: flex;
            width: 100%;
            height: 100%;
            background: url('picture/bg1600-900.jpg') 100% no-repeat;
            background-size: cover;
            background-position: center top;
        }
    </style>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<c:set var="user" value="${sessionScope.user}"/>
<c:choose>
    <c:when test="${user != null}">
    <form action="/upload" method="post" enctype="multipart/form-data">
        <section>
            <div id="select-group" align="center">
                <b></b><input type="text" class="text_input" name="title" placeholder="ชื่อชีต">

                <select name="semester" id="semester-list" onchange="getSubjectWithSemester(this.value)">
                    <option selected disabled value="">ภาคการศึกษา</option>
                    <option value=1>1</option>
                    <option value=2>2</option>
                </select>

                <select name="faculty" id="faculty-list" onchange="getDepartment(this.value)" required>
                    <option selected disabled value="">เลือกคณะ</option>
                    <sql:query dataSource="${dataSource}" var="faculty_rows">
                        SELECT * from `faculty`;
                    </sql:query>
                    <c:forEach var="faculty" items="${faculty_rows.rows}">
                        <option value='${faculty.id}'>${faculty.name}</option>
                    </c:forEach>
                </select>

                <select name="department" id="department-list" onchange="getBranch(this.value)">
                    <option value=0>ภาควิชา</option>
                </select>

                <select name="branch" id="branch-list" onchange="getSubject(this.value)">
                    <option value=0>เลือกสาขาวิชา</option>
                </select>

                <select name="year" id="year-list" onchange="getSubjectWithYear(this.value)">
                    <option value=0>เลือกชั้นปี</option>
                </select>

                <select name="subject" id="subject-list">
                    <option value=0>วิชา</option>
                </select>

                <select name="term" id="term-list">
                    <option value=0>ช่วง</option>
                    <option value=1>Mid</option>
                    <option value=2>Final</option>
                </select>
            </div>
        </section>
        <input type="file" name="file" size="50" accept="application/pdf"/><br/>
        <input type="submit" value="Upload Sheet"/>
    </form>
    </c:when>
    <c:otherwise>
        <jsp:include page="Login.jsp"/>
    </c:otherwise>
</c:choose>
</body>
<script>
    function clearOption(id) {
        $(id)
            .find('option')
            .remove()
            .end()
            .append("<option value=0>--ไม่ระบุ--</option>")
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
                data: {id: val, type: "department"},
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
                data: {id: val, type: "branch"},
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
                data: {id: val, type: "year"},
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
                data: {id: val, type: "subject", semester: $("#semester-list").val()},
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
            data: {id: $("#branch-list").val(), year: year, type: "sub-year", semester: $("#semester-list").val()},
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
            data: {id: $("#branch-list").val(), year: $("#year-list").val(), type: "sub-semester", semester: val},
            success: function (data) {
                clearOption('#sub-semester');
                $("#subject-list").html(data);
            }
        });
    }
</script>
</html>
