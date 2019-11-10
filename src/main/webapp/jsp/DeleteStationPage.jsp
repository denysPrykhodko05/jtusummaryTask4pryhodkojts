<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 08.11.2019
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete station</title>
    <script src="../javaScript/StationNameValidation.js"></script>
</head>
<body>
<a href="/admin">HOME</a><br><a href="/logout">Logout</a><br>
<form name="deleteForm" method="post" onsubmit="return isCorrectStationName('deleteForm','station_name')" action="<c:url value="/admin/stationEdit/delete"/>">
Station name: <input type="text" id="station_name" name="station_name" value="${requestScope.station_name}"><br>
<input type="submit" value="Ok"><br>
</form>
<c:if test="${requestScope.success ==true}">
    <p style="color: green;">Success</p><br>
</c:if>
<c:if test="${requestScope.error==true}">
    <p style="color: red;">Incorrect input</p><br>
</c:if>
</body>
</html>
