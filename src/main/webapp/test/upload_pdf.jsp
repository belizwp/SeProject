<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
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
                    <td>คณะ</td>
                    <td>
                        <select>
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
                    <td></td>
                </tr>
                <tr>
                    <td>สาขาวิชา</td>
                    <td></td>
                </tr>
                <tr>
                    <td>ชั้นปี</td>
                    <td>
                        <select>
                            <option value="0">--ทุกชั้นปี--</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>วิชา</td>
                    <td></td>
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
</html>
