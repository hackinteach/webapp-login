<%@ page import="io.muic.cs.ooc.webapp.login.database.MySQL" %>
<%@ page import="io.muic.cs.ooc.webapp.login.model.User" %>
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

<body>
<%
    if(request.getParameter("create") != null &&
    request.getParameter("create").equals("success")){
%>
    <script type="text/javascript">
        alert('Registration successful');
    </script>
<%
    }
%>
<h2 class="form-heading" style="color:whitesmoke" align="center">Welcome, ${user.getFirstname()}</h2>
<p style="color:whitesmoke" align="center">${user.getFirstname()} ${user.getLastname()}, ${user.getEmail()}</p>
<br/>
<table class="table table-hover table-dark" align="center" style="border: 2px solid whitesmoke">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Username</th>
        <th scope="col">Firstname</th>
        <th scope="col">Lastname</th>
        <th scope="col">Email</th>
        <th scope="col">Misc</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<User> users = MySQL.getUsers();
        for (User u : users) {
    %>
    <tr style="color:whitesmoke">
        <td><%= u.getid() %>
        </td>
        <td><%= u.getUsername() %>
        </td>
        <td><%= u.getFirstname() %>
        </td>
        <td><%= u.getLastname() %>
        </td>
        <td><%= u.getEmail() %>
        </td>
        <td>
            <% User curr = (User)request.getAttribute("user");
                if(u.getUsername().equals(curr.getUsername())){ %>
                <button type="button" class="btn btn-outline-light" disabled>Delete</button>
            <%
                }else{
            %>
            <form action="/remove" method="GET" style="display: inline">
                <input type="hidden" name="removeUser" value="<%=u.getUsername()%>" />
                <button type="submit" class="btn btn-outline-danger">Delete</button>
            </form>
            <%}%>
            <%--<button type="button" class="btn btn-outline-danger">Delete</button>--%>
            <button type="button" class="btn btn-outline-primary">Edit Profile</button>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

<div class="text-center">
    <form action="/logout" method="GET" style="display: inline">
        <button class="btn btn-outline-info" type="submit">Logout</button>
    </form>
    <form action="/register" method="GET" style="display: inline">
        <button class="btn btn-outline-success" type="submit">Add User</button>
    </form>
</div>
</body>
</html>
