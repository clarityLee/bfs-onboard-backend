<%--
  Created by IntelliJ IDEA.
  User: Yun Jing
  Date: 10/11/2021
  Time: 10:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <form method="POST" action="sendTestMail">
        <label>Email:
            <input type="text" id="email" name="email">
        </label>
        <button type="submit">submit</button>
    </form>

    <c:if test="${not empty link}">
        Mail sent. This is the link that will show in the user's email box:<br/>
        <a href="${link}">${link}</a>
    </c:if>

</head>
<body>

</body>
</html>
