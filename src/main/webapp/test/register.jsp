<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
</head>
<body>
<jsp:include page="menu.html"/>
<form action="/register" method="post">
    Username: <input type="text" name="username"><br>
    Password: <input type="password" name="password"><br>
    Fname: <input type="text" name="fname"><br>
    Lname: <input type="text" name="lname"><br>
    Email: <input type="text" name="email"><br>
    <input type="submit" value="Register">
</form>
</body>
</html>