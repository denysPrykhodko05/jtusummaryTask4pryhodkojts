<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 08.11.2019
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit route</title>
    <script src="../javaScript/RouteNumberValidation.js"></script>
</head>
<body>
<a href="/admin">HOME</a><br><a href="/logout">Logout</a><br>
<c:if test="${requestScope.input==false || requestScope.input ==null}">
    <form method="get" action="<c:url value="/admin/routeEdit"/>" onsubmit="return isCorrectNumber()">
        Route number: <input type="number" id="number" name="route_number" value="${requestScope.route_number}"><br>
        Change:<br>
        <input type="radio" name="choice" value="train">Train<br>
        <input type="radio" name="choice" value="startStation">Start station<br>
        <input type="radio" name="choice" value="finalStation">Final station<br>
        <input type="submit" value="Ok">
    </form>

    <c:if test="${requestScope.errorFindRoute == true}">
        <p style="color: red;">This route doesn't exist</p><br>
    </c:if>

    <c:if test="${requestScope.errorNothingChosen == true}">
        <p style="color: red;">Choose one option</p><br>
    </c:if>

    <c:if test="${requestScope.errorIncorrectRouteInput==true}">
        <p style="color: red;">Incorrect route input</p>
    </c:if>
</c:if>

<c:choose>
    //update train
    <c:when test="${requestScope.train==true}">
        <c:forEach var="train" items="${requestScope.trainList}">
            <form method="post">
                Train №<c:out value="${train.id}"/><br>
                Amount of economy class: <c:out value="${train.economy_class}"/><br>
                Amount of common: <c:out value="${train.common}"/><br>
                Amount of compartment: <c:out value="${train.compartment}"/><br>
                <input hidden name="id" value="${train.id}">
                <input type="hidden" name="choice" value="train">
                <input type="hidden" name="route_number" value="${requestScope.route_number}">
                <input type="submit" value="Add"><br>
            </form>
            <hr>
        </c:forEach>
    </c:when>

    //update start station
    <c:when test="${requestScope.startStation == true}">
        <form method="post">
            New start station: <input type="text" name="name"><br>
            Arrive time: <input type="date" name="arriveDate">  <input type="time" name="arriveTime"><br>
            <input type="submit" value="Ok">
        </form>
    </c:when>

    //update final station
    <c:when test="">

    </c:when>
</c:choose>
<c:if test="${requestScope.errorUpdate==true}">
    <p style="color: red;">Something gone wrong, try later!</p><br>
</c:if>
</body>
</html>
