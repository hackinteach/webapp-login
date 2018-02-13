<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--  Created by IntelliJ IDEA.
  User: hackinteachk.
  Date: 3/2/2018 AD
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Create Account</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/css/common.css" rel="stylesheet">

</head>


<body>
<c:if test="${not empty error}">
    <script type="text/javascript">
        alert('${error}');
    </script>
</c:if>

<div class="container">
    <form method="POST" action="/register" class="form-signin">
        <h2 class="form-heading" style="color:whitesmoke; text-align: center">Create Account</h2>

        <div class="form-group">
            <input name="username" type="text" class="form-control" placeholder="Username"
                   autofocus="true"/>
            <input name="firstname" type="text" class="form-control" placeholder="First Name"/>
            <input name="lastname" type="text" class="form-control" placeholder="Last Name"/>
            <input name="email" type="email" class="form-control" placeholder="Email"/>
            <input name="password" type="password" class="form-control" placeholder="Password"/>
            <input name="verifyPassword" type="password" class="form-control" placeholder="Type Password Again"/>

            <div class="text-center">
                <form action="/register" method="post">
                    <button class="btn btn-outline-light" type="submit">Register</button>
                </form>
                <button class="btn btn-outline-info" type="button" onclick="window.location.href='/login'">Cancel</button>
            </div>
        </div>
    </form>

</div>
</body>
</html>
