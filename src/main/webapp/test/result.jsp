<%--
  Created by IntelliJ IDEA.
  User: Belize
  Date: 13/12/2017 AD
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Search Result</title>
</head>
<body>
<jsp:include page="menu.html"/>
found ${requestScope.result.size()} sheets<br>
<c:forEach var="sheet" items="${requestScope.result}">
    <b>${sheet.title}</b> | by ${sheet.create_by} <br>
</c:forEach>
</body>
</html>
