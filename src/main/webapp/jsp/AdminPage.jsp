
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Admin Page</title>
</head>
<body>
<a href="/logout"><fmt:message key="index.Logout"/></a><br>
<h2><fmt:message key="admin.station"/></h2>

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

<h2><fmt:message key="index.route"/></h2>
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
