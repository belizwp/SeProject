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

        @media print, screen and (min-width: 1081px) {
            body {
                display: flex;
                width: 100%;
                height: 100%;
                background: url('picture/cac2.jpg') 100% no-repeat;
                background-size: cover;
                background-position: center top;
            }
        }

        @media print, screen and (max-width: 1080px) {
            body {
                display: flex;
                width: 100%;
                height: 100%;
                background: url('picture/cac.jpg') 100% no-repeat;
                background-size: cover;
                background-position: center top;
            }
        }
    </style>
</head>
<body>

<form action="#">
    <div class="show_body" align="center">
        <div class="middle">
            <h6>เลือกรายชื่อวิชา</h6>
            <center>
                <table class="time">
                    <th class="tablinks active text-center" onclick="openSubject(event, 'midterm')">เทอม 1</th>
                    <th class="tablinks text-center" onclick="openSubject(event, 'final')">เทอม 2</th>
                </table>
            </center>

            <ul class="menu2" id="midterm">
                <li>
                    <table class="sub">
                        <c:choose>
                            <c:when test="${requestScope.first.size() > 0}">
                                <c:forEach var="subject" items="${requestScope.first}">
                                    <tr>
                                        <td>
                                            <a href="/viewSheetAll?subject_id=${subject.id}">${subject.id} ${subject.name}</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td>
                                        <center>ไม่พบวิชาสำหรับเทอมนี้</center>
                                    </td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </table>
                </li>
            </ul>

            <ul class="menu2" id="final">
                <li>
                    <table class="sub">
                        <c:choose>
                            <c:when test="${requestScope.first.size() > 0}">
                                <c:forEach var="subject" items="${requestScope.second}">
                                    <tr>
                                        <td><a href="sheet.html">${subject.id} ${subject.name}</a></td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td>
                                        <center>ไม่พบวิชาสำหรับเทอมนี้</center>
                                    </td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </table>
                </li>
            </ul>

        </div>
    </div>
</form>
<jsp:include page="navbar.html"/>
<script type="text/javascript">
    function openSubject(evt, subject) {
        var i, tabcontent, tablinks;
        tabcontent = document.getElementsByClassName("menu2");
        for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablinks");
        for (i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" active", "");
        }
        document.getElementById(subject).style.display = "block";
        evt.currentTarget.className += " active";
    }
</script>

</body>
</html>
