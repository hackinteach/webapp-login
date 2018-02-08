<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body style="background-color:#00589F"></body>

<c:if test="${invalidLogin}">
    <script type="text/javascript">
        alert("Invalid Login");
    </script>
</c:if>

<c:if test="${emptyField}">
    <script type="text/javascript">
        alert("username and password can not be empty");
    </script>
</c:if>

<h2 align="center" style="color:white">Login</h2>
<form action="/login" method="post" class="mx-auto" style="width:30%">
    <div class="form-group">
        <label style="color:white">Username</label>
        <input type="text" class="form-control" name="username" placeholder="Username">
        <%--<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>--%>
    </div>
    <div class="form-group">
        <label style="color:white">Password</label>
        <input type="password" class="form-control" name="password" placeholder="Password">
    </div>
    <button type="submit" formaction="/login" class="btn btn-primary">Submit</button>
    <button type="submit" formaction="/register" formmethod="get" class="btn btn-primary">Register</button>
</form>
</span>
</body>
</html>
