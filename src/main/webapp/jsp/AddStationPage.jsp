<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 07.11.2019
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add station</title>
</head>
<body>
<a href="/admin">HOME</a><br><a href="/logout">Logout</a><br>
<form method="post" action="<c:url value="/admin/stationEdit/add"/>">
    Name: <input type="text" name="name" value="${requestScope.station_name}"><br>
    Depart date and time: <input type="date" name="depart_date"> <input type="time" name="depart_time" value="00:00"><br>
    Arrive date and time: <input type="date" name="arrive_date"> <input type="time" name="arrive_time" value="00:00"><br>
    Route: <input type="number" name="route" value="${requestScope.route_id}"><br>
    <input type="submit" value="OK">
</form>
<c:if test="${requestScope.success == true}">
    <p style="color: green">Success</p>
</c:if>
<c:if test="${requestScope.error == true}">
    <c:out value="${requestScope.errorRoute}"/><br>
    <c:out value="${requestScope.errorName}"/><br>
    <c:out value="${requestScope.errorDepart_date}"/><br>
    <c:out value="${requestScope.errorArrive_date}"/><br>
</c:if>
</body>
</html>
