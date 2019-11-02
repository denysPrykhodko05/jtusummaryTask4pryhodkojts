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
            <input type="text" name="from" value="${requestScope.from}">
        </div>
        <div>
            <div>To</div>
            <input type="text" name="to" value="${requestScope.to}"><br>
        </div>

        <input type="date" id="vDate" min="1970-01-01" max="1970-01-01"> <br>
        <script type="text/javascript" src="javaScript/GetTodayDay.js"></script>
        <!--<script>
            var d = new Date();
            var day = d.getDate();
            var dayMax = day;
            var month = d.getMonth()+1;
            var monthMax = month+3;
            var year = d.getFullYear();
            var yearMax = year;
            if (month < 10)
                month = "0" + month;

            if(monthMax>12){
                monthMax = monthMax-12;
                yearMax+=1;
            }

            if(monthMax == 2 && dayMax >28){
                monthMax+=1;
                dayMax="01";
            }

            if (monthMax < 10)
                monthMax = "0" + monthMax;

            if (day < 10)
                day = "0" + day;

            if (dayMax < 10)
                dayMax = "0" + dayMax;

            var date = year+"-"+month+"-"+day;
            var dateMax = yearMax+"-"+monthMax+"-"+dayMax;
            var dateH = document.querySelector('#vDate');
            dateH.setAttribute('min',date.toString());
            dateH.setAttribute('max', dateMax.toString());
        </script>-->
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
                    <hr>
                </td>
                <td>${route.timeInRoad}</td>
                <td>
                    <form method="post">
                        <input type="number" hidden name="id" value="${route.trainId}" />
                        <c:if test="${route.economy_class>0}">
                            <input type="submit" name="economy_class" value="economy class: ${route.economy_class * 58}"><br>
                        </c:if>
                        <c:if test="${route.compartment>0}">
                            <input type="submit" name="compartment" value="compartment: ${route.compartment * 36}"><br>
                        </c:if>
                        <c:if test="${route.common>0}">
                            <input type="submit" name="common" value="common: ${route.common * 68}"><br>
                        </c:if>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
