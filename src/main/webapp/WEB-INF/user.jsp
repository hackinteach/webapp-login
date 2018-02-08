<%--
  Created by IntelliJ IDEA.
  User: hackinteachk.
  Date: 5/2/2018 AD
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Success</title>
</head>
<body>
<h2>Login Success</h2>
<h2>Welcome, ${LOGIN_USER.getUsername()}</h2>
<form method="get" action="/logout">
    <input type="submit" value="logout">
</form>
</body>
</html>
