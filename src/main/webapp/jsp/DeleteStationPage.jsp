<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:setLocale value="ru" />
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="deleteStation.title"/></title>
    <script src="../javaScript/StationNameValidation.js"></script>
</head>
<body>
<a href="/admin"><fmt:message key="login.home"/></a><br><a href="/logout"><fmt:message key="index.Logout"/></a><br>
<form name="deleteForm" method="post" onsubmit="return isCorrectStationName('deleteForm','station_name')" action="<c:url value="/admin/stationEdit/delete"/>">
<fmt:message key="station.stationName"/>: <input type="text" id="station_name" name="station_name" value="${requestScope.station_name}"><br>
<input type="submit" value="Ok"><br>
</form>
<c:if test="${requestScope.success ==true}">
    <p style="color: green;"><fmt:message key="addStation.success"/></p><br>
</c:if>
<c:if test="${requestScope.error==true}">
    <p style="color: red;"><fmt:message key="station.inputError"/></p><br>
</c:if>
</body>
</html>
