<%--  Created by IntelliJ IDEA.
  User: hackinteachk.
  Date: 3/2/2018 AD
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register new User</title>
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">--%>
</head>
<body>

<c:if test="${error}">
    <script type="text/javascript">
        alert("Failed to register, please try again.");
    </script>
</c:if>
<div class="container-fluid">
    <form action="/register" method="post">
        username: <br/>
        <input type="text" name="username">
        <br/>
        password: <br/>
        <input type="text" name="password">
        <br><br/>
        <input type="submit" value="cancel" formmethod="get" formaction="/login">
        <input type="submit" value="Submit">
    </form>
</div>
</body>
</html>
