<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>

    <title>Login</title>

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

    <form method="POST" action="/login" class="form-signin">
        <h2 class="form-heading" style="color:whitesmoke; text-align: center">Log in</h2>

        <div class="form-group">
            <input name="username" type="text" class="form-control" placeholder="Username"
                   autofocus="true"/>
            <input name="password" type="password" class="form-control" placeholder="Password"/>
            <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
            <button class="btn btn-lg btn-primary btn-block" type="button" onclick="window.location.href='/register'">Create an account</button>
            <%--<h4 class="text-center"><a href="/register">Create an account</a></h4>--%>
        </div>

    </form>

</div>
</body>
</html>
