<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Test</title>
</head>
<body>
<jsp:include page="menu.html"/>
<sql:query dataSource="${dataSource}" var="sheet_rows">
    SELECT * from `sheet`;
</sql:query>
<c:forEach var="sheet" items="${sheet_rows.rows}">
    <a href="/viewSheet?id=${sheet.id}"><b>${sheet.title}</b> | by ${sheet.create_by}</a><br>
</c:forEach>
</body>
</html>
