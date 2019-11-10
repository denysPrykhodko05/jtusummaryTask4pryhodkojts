<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="admin.editStation"/></title>
    <script src="../javaScript/StationNameValidation.js"></script>
    <script>
        function confirmUpdate() {
            var update = confirm("Do you want confirm this update?");
            alert(update);
            return update;
        }
    </script>
</head>
<body>
<a href="/admin"><fmt:message key="login.home"/></a><br><a href="/logout"><fmt:message key="index.Logout"/></a><br>
<c:choose>
    <c:when test="${requestScope.input == null || requestScope.input == false}">
        <form name="stationNameForm" method="get" action="<c:url value="/admin/stationEdit/edit"/>"
              onsubmit="return isCorrectStationName('stationNameForm','station_name')">
            <input type="hidden" name="command" value="Edit command">
            <fmt:message key="station.stationName"/>: <input type="text" name="station_name"><br>
            <input type="submit" value="Ok">
            <c:if test="${requestScope.input == false}">
                <p style="color: red"><fmt:message key="station.inputError"/></p><br>
            </c:if>

        </form>
    </c:when>

    <c:when test="${requestScope.input == true}">
        <c:forEach var="route" items="${requestScope.routeList}">
            <form name="stationNameForm" method="get" action="<c:url value="/admin/stationEdit/edit"/>">
                <fmt:message key="station.stationName"/> <c:out value="${route.name}"/><br>

                <c:if test="${route.arrive_time != null}">
                    <fmt:message key="index.arrive"/>: <c:out value="${route.arrive_time}"/><br>
                </c:if>

                <c:if test="${route.depart_time != null}">
                    <fmt:message key="index.depart"/>: <c:out value="${route.depart_time}"/><br>
                </c:if>

                <fmt:message key="index.route"/> № <c:out value="${route.route_id}"/><br>
                <input hidden name="station_choose" value="true">
                <input hidden name="id" value="${route.id}">
                <input hidden name="arrive_time" value="${route.arrive_time}">
                <input hidden name="depart_time" value="${route.depart_time}">
                <input hidden name="name" value="${route.name}">
                <input hidden name="route_id" value="${route.route_id}">
                <input type="submit" value="<fmt:message key="index.choose"/>">
            </form>
            <hr>
        </c:forEach>
    </c:when>

</c:choose>


<c:if test="${requestScope.station_chosen == true}">
    <c:if test="${requestScope.errorName != true && requestScope.errorTime != true}">
        <fmt:message key="station.id"/>: <c:out value="${requestScope.id}"/><br>
        <fmt:message key="station.stationName"/>: <c:out value="${requestScope.name}"/><br>
        <fmt:message key="index.arrive"/>: <c:out value="${requestScope.arrive_time}"/><br>
        <fmt:message key="index.depart"/>: <c:out value="${requestScope.depart_time}"/><br>
    </c:if>

    <form method="post" name="stationNameForm" onsubmit="return confirmUpdate()"
          onsubmit="return isCorrectStationName('stationNameForm','newName')">
        <hr>
        <input type="hidden" id="idOld" name="idOld" value="${requestScope.id}">
        <input type="hidden" id="route_id" name="route_id" value="${requestScope.route_id}">
        <fmt:message key="station.stationName"/>: <input type="text" name="newName" value="${requestScope.newName}"><br>
        <fmt:message key="ticket.dateAndTimeDepart"/>: <input type="date" id="departDate" min="" max="" name="newDepartDate"> <input type="time"
                                                                                                            name="NewDepartTime"
                                                                                                            value="00:00"><br>
       <fmt:message key="station.arriveDateAndTime"/>: <input type="date" id="arriveDate" min="" max="" name="newArriveDate"> <input type="time"
        name="NewArriveTime"
        value="00:00"><br>

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
        <input type="submit" value="Ok">
    </form>
    <c:if test="${requestScope.errorName==true}">
        <p style="color: red;"><fmt:message key="station.inputError"/></p>
    </c:if>
    <c:if test="${requestScope.errorTime==true}">
        <p style="color: red;"><fmt:message key="station.inputError"/></p>
    </c:if>
</c:if>

</body>
</html>
