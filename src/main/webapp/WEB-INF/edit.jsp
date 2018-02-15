<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>

    <title>Login</title>

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

<c:if test="${not empty error}">
    <script type="text/javascript">
        bootbox.alert('${error}');
    </script>
</c:if>

<div class="container">

    <form method="POST" action="/login" class="form-signin">
        <h2 class="form-heading" style="color:whitesmoke; text-align: center">Log in</h2>

        <div class="form-group">
            <input name="username" type="text" class="form-control" placeholder="Username"
                   autofocus="true"/>
            <input name="password" type="password" class="form-control" placeholder="Password"/>
            <div class="text-center">
                <form action="/login" method="post">
                    <button type="submit" class="btn btn-outline-primary">Login</button>
                </form>
                <button class="btn btn-outline-success" type="button" onclick="window.location.href='/register'">Create
                    an account
                </button>
            </div>
        </div>

    </form>

</div>
</body>
</html>
