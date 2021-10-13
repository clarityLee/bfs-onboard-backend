<%--
  Created by IntelliJ IDEA.
  User: Yun Jing
  Date: 10/12/2021
  Time: 4:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form method="post" action="/login">
    <div>
        <label>
            Username:
            <input type="text" name="username">
        </label>
    </div>
    <div>
        <label>
            Password:
            <input type="password" name="password">
        </label>
    </div>
    <button type="submit">Login</button>
</form>
</body>
</html>
