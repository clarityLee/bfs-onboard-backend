<%--
  Created by IntelliJ IDEA.
  User: Yun Jing
  Date: 10/12/2021
  Time: 3:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<form method="post" action="/login/register">
    <div>
        <label>
            Email:
            <input type="text" name="email" value="${param.email}">
        </label>
    </div>
    <div>
        <label>
            Registration token:
            <input type="text" name="token" value="${param.token}">
        </label>
    </div>
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
    <button type="submit">Register</button>
</form>
</body>
</html>
