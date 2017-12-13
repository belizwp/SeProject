<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="menu.html"/>
<c:set var="user" value="${sessionScope.user}"/>
<c:choose>
    <c:when test="${user != null}">
        <form action="/upload" method="post" enctype="multipart/form-data">
            <input type="hidden" name="type" value="pdf"/>
            <table border="1">
                <tbody>
                <tr>
                    <td>ชื่อชีต</td>
                    <td><input type="text" name="title" placeholder="Title"/><br></td>
                </tr>
                <tr>
                    <td>ภาคการศึกษา</td>
                    <td>
                        <select name="semester" id="semester-list" onchange="getSubjectWithSemester(this.value)">
                            <option value=0>--ไม่ระบุ--</option>
                            <option value=1>1</option>
                            <option value=2>2</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>คณะ</td>
                    <td>
                        <select name="faculty" id="faculty-list" onchange="getDepartment(this.value)">
                            <option value=0>--ไม่ระบุ--</option>
                            <sql:query dataSource="${dataSource}" var="faculty_rows">
                                SELECT * from `faculty`;
                            </sql:query>
                            <c:forEach var="faculty" items="${faculty_rows.rows}">
                                <option value='${faculty.id}'>${faculty.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>ภาควิชา</td>
                    <td>
                        <select name="department" id="department-list" onchange="getBranch(this.value)">
                            <option value=0>--ไม่ระบุ--</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>สาขาวิชา</td>
                    <td>
                        <select name="branch" id="branch-list" onchange="getSubject(this.value)">
                            <option value=0>--ไม่ระบุ--</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>ชั้นปี</td>
                    <td>
                        <select name="year" id="year-list" onchange="getSubjectWithYear(this.value)">
                            <option value=0>--ไม่ระบุ--</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>วิชา</td>
                    <td>
                        <select name="subject" id="subject-list">
                            <option value=0>--ไม่ระบุ--</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>ช่วง</td>
                    <td>
                        <select name="term" id="term-list">
                            <option value=0>--ไม่ระบุ--</option>
                            <option value=1>Mid</option>
                            <option value=2>Final</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>upload file</td>
                    <td>
                        <input type="file" name="file" size="50" accept="application/pdf"/><br/>
                    </td>
                </tr>
                </tbody>
            </table>
            <input type="submit" value="Upload Sheet"/>
        </form>
    </c:when>
    <c:otherwise>
        <jsp:include page="login_required.html"/>
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
