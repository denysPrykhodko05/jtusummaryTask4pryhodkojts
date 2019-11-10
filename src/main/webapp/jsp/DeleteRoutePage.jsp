<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="admin.deleteRoute"/></title>
</head>
<body>
<form method="post" action="<c:url value="/admin/routeDelete"/>">
    <fmt:message key="editRoute.routeNumber"/> : <input type="number" name="route" value="route"><br>
    <input type="submit" value="OK">
</form>
<c:if test="${requestScope.errorInput == true}">
    <p style="color: red;"><fmt:message key="editRoute.IncorrectInput"/></p>
</c:if>
</body>
</html>
