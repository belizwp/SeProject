<%--
  Created by IntelliJ IDEA.
  User: Belize
  Date: 13/12/2017 AD
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page isELIgnored="false" %>
<html>
<c:set var="sheet" value="${requestScope.sheet}"/>
<c:set var="pdf" value="${requestScope.pdf}"/>
<head>
    <title>${sheet.title}</title>
</head>
<body>
<object data="${pdf.path}" type="application/pdf" width="100%" height="100%">
    <p><b>Example fallback content</b>: This browser does not support PDFs. Please download the PDF to view it: <a href="${pdf.path}">Download PDF</a>.</p>
</object>
</body>
</html>
