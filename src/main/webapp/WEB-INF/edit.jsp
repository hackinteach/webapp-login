<%@ page import="io.muic.cs.ooc.webapp.login.database.MySQL" %>
<%@ page import="io.muic.cs.ooc.webapp.login.model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>

    <title>Edit Profile</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%--JS--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="${contextPath}/js/bootbox.min.js"></script>
    <script type="text/javascript" src="${contextPath}/js/bootstrap.js"></script>
    <%--CSS--%>
    <link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/css/common.css" rel="stylesheet">

</head>
<body>

<form action="/edit" method="post">

    <table class="table table-hover table-dark" align="center" style="border: 2px solid whitesmoke">
        <thead>
        <tr>
            <th scope="col">Field</th>
            <th scope="col">Information</th>
            <th scope="col">Edit</th>
        </tr>
        </thead>

        <%
            String username = request.getParameter("editUser");
            User u = MySQL.getUserbyUsername(username);
            if (u != null) {
        %>
        <tr style="color:whitesmoke">
            <td>
                Username
            </td>
            <td>
                <%= u.getUsername() %>
            </td>
            <td>
                <input name="username" type="text" class="form-control" placeholder="new username"/>
            </td>
        </tr>

        <tr style="color:whitesmoke">
            <td>
                First Name
            </td>
            <td>
                <%= u.getFirstname() %>
            </td>
            <td>
                <input name="firstname" type="text" class="form-control" placeholder="new first name"/>
            </td>
        </tr>

        <tr style="color:whitesmoke">
            <td>
                Last Name
            </td>
            <td>
                <%= u.getLastname() %>
            </td>
            <td>
                <input name="lastname" type="text" class="form-control" placeholder="new last name"/>
            </td>
        </tr>

        <tr style="color:whitesmoke">
            <td>
                Email
            </td>
            <td>
                <%= u.getEmail() %>
            </td>
            <td>
                <input name="email" type="text" class="form-control" placeholder="new email"/>
            </td>
        </tr>

        <tr style="color:whitesmoke">
            <td>
                New Password
            </td>
            <td>
            </td>
            <td>
                <input name="new_password" type="password" class="form-control" placeholder="new password"/>
                <input name="cf_password" type="password" class="form-control" placeholder="confirm new password"/>
            </td>
        </tr>
        <%
            }
        %>
        <tbody>
    </table>

    <div class="form-group text-center">
        <button class="btn btn-outline-light" type="submit">Update</button>
        <button class="btn btn-outline-info" type="subbmit" onclick="window.location.href='/user'">Cancel</button>
    </div>

</form>
</body>
</html>
