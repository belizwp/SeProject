<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="menu.html"/>
<h3>File Upload:</h3>
Select a file to upload: <br/>
<form action="/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file" size="50" accept="application/pdf"/>
    <br/>
    <input type="submit" value="Upload File"/>
</form>
</body>
</html>
