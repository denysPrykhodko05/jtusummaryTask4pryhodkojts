<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 04.11.2019
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:setLocale value="ru"/>
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
