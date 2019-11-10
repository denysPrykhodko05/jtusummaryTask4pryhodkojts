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
    <script src="../javaScript/StationNameValidation.js"></script>
</head>
<body>
<a href="/admin">HOME</a><br><a href="/logout">Logout</a><br>
<form name="addStationForm" method="post" action="<c:url value="/admin/stationEdit/add"/>" onsubmit="return isCorrectStationName('addStationForm','name')">
    Name: <input type="text" name="name" value="${requestScope.station_name}"><br>
    Depart date and time: <input type="date"id="departDate" min="" max="" name="depart_date"> <input type="time" name="depart_time" value="00:00"><br>
    Arrive date and time: <input type="date" id="arriveDate" min="" max="" name="arrive_date"> <input type="time" name="arrive_time" value="00:00"><br>
    <script>var today = new Date();
    var date = today.getDate();
    var dateMax = date;
    var month = today.getMonth() + 1;
    var monthMax = month + 3;
    var year = today.getFullYear();
    var yearMax = year;
    var dateD = document.querySelector('#departDate');
    var dateA = document.querySelector('#arriveDate');

    if (month < 10)
        month = "0" + month;

    if (monthMax > 12) {
        monthMax = monthMax - 12;
        yearMax += 1;
    }

    if (monthMax == 2 && dateMax > 28) {
        monthMax += 1;
        dateMax = "01";
    }

    if (monthMax < 10)
        monthMax = "0" + monthMax;

    if (date < 10)
        date = "0" + date;

    if (dateMax < 10)
        dateMax = "0" + dateMax;

    var Max = yearMax + "-" + monthMax + "-" + dateMax;
    var dateT = year + "-" + month + "-" + date;
    dateD.setAttribute('value', dateT.toString());
    dateA.setAttribute('value', dateT.toString());
    dateD.setAttribute('min', dateT.toString());
    dateD.setAttribute('max', Max.toString());
    dateA.setAttribute('min', dateT.toString());
    dateA.setAttribute('max', Max.toString());
    </script>
    Route: <input type="number" name="route" value="${requestScope.route_id}"><br>
    <input type="submit" value="OK">
</form>
<c:if test="${requestScope.success == true}">
    <p style="color: green">Success</p>
</c:if>
<c:if test="${requestScope.errorRoute == true}">
    <p style="color: red">Incorrect route</p>
</c:if>
<c:if test="${requestScope.errorTime == true}">
    <p style="color: red">Incorrect time</p>
</c:if>
<c:if test="${requestScope.error == true}">
    <c:out value="${requestScope.errorRoute}"/><br>
    <c:out value="${requestScope.errorName}"/><br>
    <c:out value="${requestScope.errorDepart_date}"/><br>
    <c:out value="${requestScope.errorArrive_date}"/><br>
</c:if>
</body>
</html>
