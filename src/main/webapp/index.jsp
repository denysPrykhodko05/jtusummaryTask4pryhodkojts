<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking</title>
    <script src="/javaScript/RouteValidation.js"></script>

</head>
<body>
<c:choose>
    <c:when test="${sessionScope.loginBool == true}">
        <a href="<c:url value='/profile'/>">Profile</a>
    </c:when>
    <c:otherwise>
        <a href="/login">Login</a>
    </c:otherwise>
</c:choose>

<form name="routeForm" method="post" action="<c:url value='/search'/>"
      onsubmit="return validateForm('routeForm','from','to')">
    <div>
        <div>
            <div>From</div>
            <input type="text" name="from" value="${requestScope.from}">
        </div>
        <div>
            <div>To</div>
            <input type="text" name="to" value="${requestScope.to}"><br>
        </div>

        <input type="date" name="date" id="vDate" min="1970-01-01" max="1970-01-01"> <br>
        <script type="text/javascript" src="javaScript/GetTodayDay.js"></script>
        <input type="time" name="time"><br>
        <input type="submit" name="Ok" value="Ok">
    </div>
</form>

<c:choose>
    <c:when test="${requestScope.foundRoute == false}">
        <div><p style="color: red">Routes didn't find</p></div>
    </c:when>
    <c:otherwise>

    </c:otherwise>
</c:choose>

<c:if test="${search==true}">
    <table border="1">
        <tr>
            <th>№ train</th>
            <th>From/To</th>
            <th>Depart/<br>Arrive</th>
            <th>In road</th>
            <th>Empty places</th>
        </tr>
        <c:forEach var="route" items="${routes}">
            <tr>
                <td>${route.trainId}</td>
                <td>${route.startStationName}-<br>${route.finalStationName}</td>
                <td>
                    depart: ${route.depart_time}<br>
                    <hr>
                    arrive: ${route.arrive_time}
                </td>
                <td>${route.timeInRoad}</td>
                <td>


                    <c:if test="${route.economy_class>0}">
                        <form method="get" action="<c:url value='/purchaseTicket'/>">
                            <input type="number" hidden name="id" value="${route.trainId}"/>
                            economy class: ${route.economy_class * 58} <input type="submit" name="economy_class" value="Choose"><br>
                        </form>
                    </c:if>
                    <c:if test="${route.compartment>0}">
                        <form method="get" action="<c:url value='/purchaseTicket'/>">
                            <input type="number" hidden name="id" value="${route.trainId}"/>
                            compartment: ${route.compartment * 36} <input type="submit" name="compartment" value="Choose"><br>
                        </form>
                    </c:if>
                    <c:if test="${route.common>0}">
                        <form method="get" action="<c:url value='/purchaseTicket'/>">
                            <input type="number" hidden name="id" value="${route.trainId}"/>
                            Common: ${route.common * 68} <input type="submit" name="common" value="Choose"><br>
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
