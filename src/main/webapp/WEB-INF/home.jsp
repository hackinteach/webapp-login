<%@ page import="io.muic.cs.ooc.webapp.servlet.RegisterServlet" %>
<%--
  Created by IntelliJ IDEA.
  User: hackinteachk.
  Date: 3/2/2018 AD
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div class="align-middle container-fluid">
    <h2><c:out value="Hi, ${username}"/></h2>
    <br/>
    <form method="get" action="/logout">
        <input type="submit" value="logout">
    </form>
</div>
</body>
</html>
