<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 09.11.2019
  Time: 23:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete route</title>
</head>
<body>
<form method="post" action="<c:url value="/admin/routeDelete"/>">
    Route number: <input type="number" name="route" value="route"><br>
    <input type="submit" value="OK">
</form>
<c:if test="${requestScope.errorInput == true}">
    <p style="color: red;">Incorrect input</p>
</c:if>
</body>
</html>
