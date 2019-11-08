<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 27.10.2019
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ticket</title>
</head>
<body>
<body><a href="/">HOME</a><a href="/logout">Logout</a>
<h2>Your ticket</h2>
<a href="<c:url value="/"/>">HOME</a>
<c:if test="${requestScope.ticket!=null}">
    <c:set var="ticket" value="${requestScope.ticket}"/>
    <table border="1">
        <caption>Ticket</caption>
        <tr>
            <th>Train_id</th>
            <th>Date and time depart</th>
            <th>Carriage</th>
            <th>Carriage number</th>
            <th>Place</th>
            <th>Start station</th>
            <th>Final station</th>
        </tr>
        <tr>
            <td><c:out value="${ticket.train_id}"/></td>
            <td><c:out value="${ticket.date}"/></td>
            <td><c:out value="${ticket.carriage}"/></td>
            <td><c:out value="${ticket.carriage_number}"/></td>
            <td><c:out value="${ticket.place}"/></td>
            <td><c:out value="${ticket.start_station}"/></td>
            <td><c:out value="${ticket.final_station}"/></td>
        </tr>

    </table>
</c:if>
</body>
</html>
