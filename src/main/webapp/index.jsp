<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking</title>
    <script src="../javaScript/RouteValidation.js"></script>
</head>
<body>
<c:choose>
    <c:when test="${sessionScope.loginBool == true}">
        <!-- put here profile icon-->
    </c:when>
    <c:otherwise>
        <a href="/login">Login</a>
    </c:otherwise>
</c:choose>

<form name="routeForm" method="post" action="/search" onsubmit="return validateForm('routeForm','from','to')">
    <div>
        <div>
            <div>From</div>
            <input type="text" name="from">
        </div>
        <div>
            <div>To</div>
            <input type="text" name="to"><br>
        </div>
        <input type="submit" name="Ok" value="Ok">
    </div>
</form>

<c:if test="${search==true}">
    <table border="1">
        <tr>
            <th>№ train</th>
            <th>From/To</th>
            <th>Arrive/<br>Depart</th>
            <th>In road</th>
            <th>Empty sits</th>
        </tr>
        <c:forEach var="route" items="${routes}">
            <tr>
            <td>${route.trainId}</td>
            <td>${route.startStationName}-<br>${route.finalStationName}</td>
            <td>arrive: ${route.arrive_time}<br>
                depart: ${route.depart_time}</td>
            <td>8:00</td>
            <td>
                <c:if test="${route.economy_class>0}">
                    economy class: ${route.economy_class}<br>
                </c:if>
                <c:if test="${route.compartment>0}">
                    compartment: ${route.compartment}<br>
                </c:if>
                <c:if test="${route.common>0}">
                    common: ${route.common}
                </c:if>
            </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
