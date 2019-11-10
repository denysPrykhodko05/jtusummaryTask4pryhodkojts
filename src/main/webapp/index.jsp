<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="index.title"/></title>
    <script src="/javaScript/RouteValidation.js"></script>

</head>
<body>
<form method="post" action="/changeLang">
    <select name="locale">
        <option value="en">en</option>
        <option selected value="ru">ru</option>
    </select><br>
    <input type="submit" value="Ok">
</form>
<c:choose>
    <c:when test="${sessionScope.loginBool == true}">
        <a href="<c:url value='/profile'/>"><fmt:message key="index.profile"/></a><br>
        <a href="/logout"><fmt:message key="index.Logout"/> </a>
    </c:when>
    <c:otherwise>
        <a href="/login"><fmt:message key="index.login"/> </a>
    </c:otherwise>
</c:choose>

<form name="routeForm" method="post" action="<c:url value='/search'/>"
      onsubmit="return validateForm('routeForm','from','to')">
    <div>
        <div>
            <div><fmt:message key="index.from"/></div>
            <input type="text" name="from" value="${requestScope.from}">
        </div>
        <div>
            <div><fmt:message key="index.to"/></div>
            <input type="text" name="to" value="${requestScope.to}"><br>
        </div>

        <input type="date" name="date" id="vDate" min="1970-01-01" max="1970-01-01" value=""> <br>
        <script type="text/javascript" src="javaScript/GetTodayDay.js"></script>
        <input type="time" name="time" value="00:00"><br>
        <input type="submit" name="Ok" value="Ok">
    </div>
</form>

<c:choose>
    <c:when test="${requestScope.foundRoute == false}">
        <div><p style="color: red"><fmt:message key="index.routeError"/></p></div>
    </c:when>
</c:choose>

<c:if test="${search==true}">
    <table border="1">
        <tr>
            <th>№ <fmt:message key="index.trainNumber"/></th>
            <th><fmt:message key="index.fromTo"/></th>
            <th><fmt:message key="index.depart"/>/<br><fmt:message key="index.arrive"/></th>
            <th><fmt:message key="index.InRoad"/></th>
            <th><fmt:message key="index.price"/></th>
            <th><fmt:message key="index.emptyPlaces"/></th>
        </tr>
        <c:forEach var="route" items="${routes}">
            <tr>
                <td>${route.trainId}<br>
                    <form method="get" action="<c:url value="/route"/>">
                        <input type="number" hidden name="id" value="${route.trainId}"/>
                        <input type="submit" value="<fmt:message key="index.route"/>">
                    </form>
                </td>
                <td>${route.startStationName}-<br>${route.finalStationName}</td>
                <td>
                    <fmt:message key="index.depart"/>: ${route.depart_time}<br>
                    <hr>
                    <fmt:message key="index.arrive"/>: ${route.arrive_time}
                </td>
                <td>${route.timeInRoad}</td>
                <td>
                    <c:if test="${route.economy_class>0}">
                        ${route.priceForEconomy} <fmt:message key="index.uah"/><br>
                        <hr>
                    </c:if>
                    <c:if test="${route.compartment>0}">
                        ${route.priceForCompartment} <fmt:message key="index.uah"/><br>
                        <hr>
                    </c:if>
                    <c:if test="${route.common>0}">
                        ${route.priceForCommon} <fmt:message key="index.uah"/>
                    </c:if>
                </td>
                <td>
                    <c:if test="${route.economy_class>0}">
                        <form method="get" action="<c:url value='/purchaseTicket'/>">
                            <input type="number" hidden name="id" value="${route.trainId}">
                            <input type="hidden" id="from" name="from" value="${route.startStationName}">
                            <input type="hidden" id="to" name="to" value="${route.finalStationName}">
                            <input type="hidden" id="price" name="price" value="${route.priceForEconomy}">
                            <input type="hidden" id="arrive_time" name="arrive_time" value="${route.arrive_time}">
                            <input type="hidden" id="depart_time" name="depart_time" value="${route.depart_time}">
                            <fmt:message key="index.economy_class"/>: ${route.economy_class * 58} <input type="submit"
                                                                                                         name="economy_class"
                                                                                                         value="<fmt:message key="index.choose"/>"><br>
                        </form>
                    </c:if>
                    <c:if test="${route.compartment>0}">
                        <form method="get" action="<c:url value='/purchaseTicket'/>">
                            <input type="number" hidden name="id" value="${route.trainId}"/>
                            <input type="hidden" id="from" name="from" value="${route.startStationName}">
                            <input type="hidden" id="to" name="to" value="${route.finalStationName}">
                            <input type="hidden" id="price" name="price" value="${route.priceForCompartment}">
                            <input type="hidden" id="arrive_time" name="arrive_time" value="${route.arrive_time}">
                            <input type="hidden" id="depart_time" name="depart_time" value="${route.depart_time}">
                            <fmt:message key="index.compartment"/> : ${route.compartment * 36} <input type="submit"
                                                                                                      name="compartment"
                                                                                                      value="<fmt:message key="index.choose"/>"><br>
                        </form>
                    </c:if>
                    <c:if test="${route.common>0}">
                        <form method="get" action="<c:url value='/purchaseTicket'/>">
                            <input type="number" hidden name="id" value="${route.trainId}"/>
                            <input type="hidden" id="from" name="from" value="${route.startStationName}">
                            <input type="hidden" id="to" name="to" value="${route.finalStationName}">
                            <input type="hidden" id="price" name="price" value="${route.priceForCommon}">
                            <input type="hidden" id="arrive_time" name="arrive_time" value="${route.arrive_time}">
                            <input type="hidden" id="depart_time" name="depart_time" value="${route.depart_time}">
                            <fmt:message key="index.common"/> : ${route.common * 68} <input type="submit" name="common"
                                                                                            value="<fmt:message key="index.choose"/>"><br>
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
