<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="editRoute.title"/></title>
    <script src="../javaScript/RouteNumberValidation.js"></script>
    <script src="../javaScript/StationNameValidation.js"></script>
</head>
<body>
<a href="/admin"><fmt:message key="login.home"/></a><br><a href="/logout"><fmt:message key="index.Logout"/></a><br>
<c:if test="${requestScope.input==false || requestScope.input ==null}">
    <form method="get" action="<c:url value="/admin/routeEdit"/>" onsubmit="return isCorrectNumber()">
         <fmt:message key="editRoute.routeNumber"/>: <input type="number" id="number" name="route_number" value="${requestScope.route_number}"><br>
        <fmt:message key="editRoute.change"/>:<br>
        <input type="radio" name="choice" value="train"><fmt:message key="index.trainNumber"/><br>
        <input type="radio" name="choice" value="startStation"><fmt:message key="ticket.startStation"/><br>
        <input type="radio" name="choice" value="finalStation"><fmt:message key="ticket.finalStation"/><br>
        <input type="radio" name="choice" value="deleteStation"><fmt:message key="editRoute.deleteStation"/><br>
        <input type="radio" name="choice" value="addStation"><fmt:message key="editRoute.addStation"/><br>
        <input type="submit" value="Ok">
    </form>

    <c:if test="${requestScope.errorFindRoute == true}">
        <p style="color: red;"></p><fmt:message key="editRoute.errorFindRoute"/><br>
    </c:if>

    <c:if test="${requestScope.errorNothingChosen == true}">
        <p style="color: red;"><fmt:message key="editRoute.nothingChosen"/></p><br>
    </c:if>

    <c:if test="${requestScope.errorIncorrectRouteInput==true}">
        <p style="color: red;"><fmt:message key="editRoute.errorIncorrectRoute"/></p>
    </c:if>
</c:if>

<c:choose>

    <c:when test="${requestScope.train==true}">
        <c:forEach var="train" items="${requestScope.trainList}">
            <form method="post">
                <fmt:message key="index.trainNumber"/> №<c:out value="${train.id}"/><br>
                 <fmt:message key="editRoute.amountOfEconomyClass"/> : <c:out value="${train.economy_class}"/><br>
                <fmt:message key="editRoute.amountOfCommon"/>: <c:out value="${train.common}"/><br>
                <fmt:message key="editRoute.amountOfCompartment"/> : <c:out value="${train.compartment}"/><br>
                <input hidden name="id" value="${train.id}">
                <input type="hidden" name="choice" value="train">
                <input type="hidden" name="route_number" value="${requestScope.route_number}">
                <input type="submit" value="<fmt:message key="editRoute.add"/>"><br>
            </form>
            <hr>
        </c:forEach>
    </c:when>

    <c:when test="${requestScope.startStation == true}">
        <form method="post" name="startStationForm"
              onsubmit="return isCorrectStationName('startStationForm','startName')">
            <fmt:message key="editRoute.newStartStation"/>: <input id="startName" type="text" name="startName"><br>
            <fmt:message key="ticket.dateAndTimeDepart"/>: <input type="date" id="vDate" name="departDate"> <input type="time" name="departTime"><br>
            <script type="text/javascript" src="../javaScript/GetTodayDay.js"></script>
            <input type="hidden" name="route_number" value="${requestScope.route_number}">
            <input type="submit" value="Ok">
        </form>
    </c:when>

    <c:when test="${requestScope.finalStation == true}">
        <form method="post" name="finalStationForm"
              onsubmit="return isCorrectStationName('finalStationForm','finalName')">
            <fmt:message key="editRoute.newStartStation"/>: <input type="text" id="finalName" name="finalName"><br>
            <fmt:message key="station.arriveDateAndTime"/>: <input type="date" id="arriveDate1" min="" max="" value="" name="arriveDate"> <input type="time" name="arriveTime" value="00:00"><br>
            <script>var today = new Date();
            var date = today.getDate();
            var dateMax = date;
            var month = today.getMonth() + 1;
            var monthMax = month + 3;
            var year = today.getFullYear();
            var yearMax = year;
            var dateA = document.querySelector('#arriveDate1');

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
            dateA.setAttribute('value', dateT.toString());
            dateA.setAttribute('min', dateT.toString());
            dateA.setAttribute('max', Max.toString());
            </script>
            <input type="hidden" name="route_number" value="${requestScope.route_number}">
            <input type="submit" value="Ok">
        </form>
    </c:when>

    <c:when test="${requestScope.deleteStation==true}">
        <form method="post" name="deleteStationForm"
              onsubmit="return isCorrectStationName('deleteStationForm','stationName')">
            <fmt:message key="station.stationName"/> : <input type="text" id="deleteName" name="stationName"><br>
            <input type="hidden" name="deleteStation" value="true">
            <input type="hidden" name="route_number" value="${requestScope.route_number}">
            <input type="submit" value="Ok">
        </form>

    </c:when><c:when test="${requestScope.addStation==true}">
    <form method="post" name="addStationForm" onsubmit="return isCorrectStationName('deleteStationForm','stationName')">
        <fmt:message key="station.stationName"/> : <input type="text" id="addStation" name="stationName"><br>
        <fmt:message key="station.arriveDateAndTime"/> : <input type="date" id="arriveDate" name="arriveDate"> <input type="time" name="arriveTime" value="00:00"><br>
        <fmt:message key="ticket.dateAndTimeDepart"/> : <input type="date" id="departDate" name="departDate"> <input type="time" name="departTime" value="00:00"><br>
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
        <input type="hidden" name="addStation" value="true">
        <input type="hidden" name="route_number" value="${requestScope.route_number}">
        <input type="submit" value="Ok">

        <c:if test="${requestScope.errorAddStationName == true}">
                <p style="color: red"><fmt:message key="editRoute.IncorrectInput"/></p><br>
        </c:if>
    </form>
</c:when>
</c:choose>
<c:if test="${requestScope.errorUpdate==true}">
    <p style="color: red;"><fmt:message key="editRoute.errorUpdate"/></p><br>
</c:if>
</body>
</html>
