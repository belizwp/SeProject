<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Sheet and Share</title>

    <link rel="stylesheet" href="foundation.css">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="upload.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style type="text/css">
        @media print, screen and (max-width: 1280px) {
            body {
                display: flex;
                width: 100%;
                height: 100%;
                background: url('picture/up.jpg') 100% no-repeat;
                background-size: cover;
                background-position: center top;
            }
        }

        @media print, screen and (min-width: 1280px) {
            body {
                display: flex;
                width: 100%;
                height: 100%;
                background: url('picture/up2.jpg') 100% no-repeat;
                background-size: cover;
                background-position: center top;
            }
        }
    </style>
</head>
<body>
<c:set var="subject" value="${requestScope.subjectView}"/>
<form action="/upload" method="post" enctype="multipart/form-data">
    <input type="hidden" name="type" value="pdf">
    <input type="hidden" name="subject" value="${subject.id}">
    <input type="hidden" name="branch" value="${subject.branchId}">
    <input type="hidden" name="department" value="${subject.departmentId}">
    <input type="hidden" name="faculty" value="${subject.facultyId}">
    <input type="hidden" name="semester" value="${subject.semester}">
    <input type="hidden" name="year" value="${subject.year}">
    <input type="hidden" name="term" value="0">
    <div id="icon-upload">
        <input type="file" name="file" size="50" accept="application/pdf" required style="margin-left: -100px;
                                         width: 300px;position: relative;
                                         padding: 0;
                                         outline: none;
                                         opacity: 0;
                                         cursor: pointer;
                                         height: 200px;">
        <center>
            <input type="text" name="title" class="text_input" placeholder="กรอกชื่อไฟล์" required>
            <input type="image" src="picture/interface.svg" id="btn_upload" class="img_up" border="0"
                   alt="Submit"/>
        </center>
    </div>
</form>
<jsp:include page="navbar.html"/>
</body>
<script type="text/javascript">
    $(document).ready(function () {
        $('form input').change(function () {
            $('form p').text(this.files.length + " file(s) selected");
        });
    });
</script>
</html>
