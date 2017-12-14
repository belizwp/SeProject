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
        td:hover {
            background-color: #535353;
            color: white;
        }

        th:hover {
            background-color: #ffffff00;
            color: #535353;
        }

        body {
            display: flex;
            width: 100%;
            height: 100%;
            background: url('picture/bg2.jpg') 100% no-repeat;
            background-size: cover;
            background-position: center top;
        }

        a {
            color: #535353;
        }

    </style>
</head>
<body>
<c:set var="subject" value="${requestScope.subjectView}"/>
<form action="/collector" method="post">
    <div class="show_body" align="center">
        <h4>วิชา ${subject.name} รหัสวิชา ${subject.id}</h4>
        <h6>ชั้นปีที่ ${subject.year} ภาคเรียนที่ ${subject.semester} สาขา ${subject.branchName}
            ภาควิชา ${subject.departmentName} คณะ ${subject.facultyName}</h6>
        <table style="width: 85%; margin-top: 40px;">
            <thead>
            <th style="width: 40%;">
                <center>ชื่อไฟล์</center>
            </th>
            <th>
                <center>ผู้อัพโหลด</center>
            </th>
            <th>
                <center>แก้ไขล่าสุด</center>
            </th>
            </thead>
            <c:forEach var="sheet" items="${requestScope.sheetList}">
                <tr>
                    <td>${sheet.title}</td>
                    <td>
                        <center>${sheet.create_by}</center>
                    </td>
                    <td>
                        <center>${sheet.updateTime}</center>
                    </td>
                    <td class="view">
                        <center><a target="_blank" href="/viewSheet?id=${sheet.id}">VIEW</a></center>
                    </td>
                    <td class="download">
                        <center><a href="${sheet.pdfPath}" download>DOWNLOAD</a></center>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${sessionScope.user != null}">
            <input type="hidden" name="subjectId" value="${subject.id}">
            <input type="hidden" name="branchId" value="${subject.branchId}">
            <input type="hidden" name="departmentId" value="${subject.departmentId}">
            <input type="hidden" name="facultyId" value="${subject.facultyId}">
            <input type="hidden" name="semester" value="${subject.semester}">
            <input type="hidden" name="year" value="${subject.year}">
            <input id="link_upload" type="image" align="right" src="picture/002-file-add-button-outline.svg" border="0" alt="Submit"/>
        </c:if>
    </div>
</form>
<jsp:include page="navbar.html"/>
</body>
</html>