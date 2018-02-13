<%@ page import="io.muic.cs.ooc.webapp.login.model.User" %>
<%@ page import="io.muic.cs.ooc.webapp.login.database.MySQL" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hackinteachk.
  Date: 5/2/2018 AD
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Welcome</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/css/common.css" rel="stylesheet">

</head>

<body >

<h2 class="form-heading" style="color:whitesmoke" align="center">Welcome, ${user.getFirstname()}</h2>
<br/>
<table class="table table-hover table-dark w-50" align="center" style="border: 2px solid whitesmoke">
    <thead>
    <tr>
        <th scope="col">Username</th>
        <th scope="col">Firstname</th>
        <th scope="col">Lastname</th>
        <th scope="col">Email</th>
    </tr>
    </thead>
    <tbody>
<%
    List<User> users = MySQL.getUsers();
    for(User u : users){
%>
<tr style="color:whitesmoke">
    <td><%= u.getUsername() %></td>
    <td><%= u.getFirstname() %></td>
    <td><%= u.getLastname() %></td>
    <td><%= u.getEmail() %></td>
</tr>
<%
    }
%>
    </tbody>
</table>
<div class="text-center">
<form action="/logout" method="get">
    <button style="width:5em;" class="btn btn-primary" type="submit">Logout</button>
</form>
</div>
</body>
</html>
