<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 27.10.2019
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Page</title>
</head>
<body>
<h2>Station</h2>

<a href="/logout">Logout</a>
<form method="get" action="<c:url value="/admin/stationEdit"/>">
    <label><input type="submit" name="command" value="Edit station" ></label>
</form>

<form method="get" action="<c:url value="/admin/stationEdit"/>">
    <label><input type="submit" name="command" value="Add station" ></label>
</form>

<form method="get" action="<c:url value="/admin/stationEdit"/>">
    <label><input type="submit" name="command" value="Delete station" ></label><br>
</form>
<hr>

<h2>Route</h2>
<form method="get" action="<c:url value="/admin/stationEdit"/>">
    <label><input type="submit" name="command" value="Edit route" ></label>
</form>

<form method="get" action="<c:url value="/admin/stationEdit"/>">
    <label><input type="submit" name="command" value="Add route" ></label>
</form>

<form method="get" action="<c:url value="/admin/stationEdit"/>">
    <label><input type="submit" name="command" value="Delete route" ></label><br>
</form>

</body>
</html>
