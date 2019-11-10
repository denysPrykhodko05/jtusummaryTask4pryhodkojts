<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add route</title>
    <script src="../javaScript/AddRouteValidation.js"></script>
</head>
<body>

<form name="addRouteForm" method="post" onsubmit="return routeValidation('addRouteForm','startName','finalName')">
    <fmt:message key="ticket.startStation"/> : <input type="text" name="startName" value="${requestScope.startName}"> <input type="date" id="departDate" name="departDate"
                                                                    value=""> <input type="time"
                                                                                     name="departTime"
                                                                                     value="00:00"><br>

    <fmt:message key="ticket.finalStation"/> : <input type="text" name="finalName" value="${requestScope.finalName}"> <input type="date" id="arriveDate" name="arriveDate"
                                                                    value=""> <input type="time"
                                                                                     name="arriveTime"
                                                                                     value="00:00"><br>
    <script src="../javaScript/AddRouteDateValidation.js"></script>

    <c:forEach var="train" items="${requestScope.trainList}">
        <input type="radio" name="train" value="${requestScope.train.id}">Train №<c:out value="${train.id}"/><br>
        <input hidden name="id" value="${train.id}">
        <hr>
    </c:forEach>
    <input type="submit" value="Ok">
</form>

<c:if test="${requestScope.errorStart == true}">
    <p style="color: red;"><fmt:message key="edit.errorStartStationName"/></p>
</c:if>

<c:if test="${requestScope.errorTime == true}">
    <p style="color: red;"><fmt:message key="edit.errorTime"/></p>
</c:if>

<c:if test="${requestScope.errorFinal == true}">
    <p style="color: red;"><fmt:message key="edit.errorFinalStationName"/></p>
</c:if>

<c:if test="${requestScope.errorArrive == true}">
    <p style="color: red;"><fmt:message key="edit.errorTime"/></p>
</c:if>

<c:if test="${requestScope.errorDepart == true}">
    <p style="color: red;"><fmt:message key="edit.errorTime"/></p>
</c:if>

<c:if test="${requestScope.errorTrain == true}">
    <p style="color: red;"><fmt:message key="editRoute.errorTrain"/></p>
</c:if>

</body>
</html>
