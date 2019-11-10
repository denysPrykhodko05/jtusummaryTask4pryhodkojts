<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Route</title>
</head>
<body>
<body><a href="/"><fmt:message key="login.home"/></a>
<c:forEach var="route" items="${requestScope.route}">
    <div style="display: flex;"> <div style="">${route.arrive_time}<br>${route.depart_time}</div><div style="align-self: center;">${route.name}</div></div><br><br>
</c:forEach>
</body>
</html>
