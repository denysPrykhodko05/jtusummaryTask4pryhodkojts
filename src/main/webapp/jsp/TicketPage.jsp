<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="userprofile.ticket"/></title>
</head>
<body>
<body><a href="/"><fmt:message key="login.home"/></a><br><a href="/logout"><fmt:message key="index.Logout"/></a>
<h2><fmt:message key="ticket.yourTicket"/></h2>
<c:if test="${requestScope.ticket!=null}">
    <c:set var="ticket" value="${requestScope.ticket}"/>
    <table border="1">
        <caption><fmt:message key="userprofile.ticket"/></caption>
        <tr>
            <th>№ <fmt:message key="index.trainNumber"/></th>
            <th><fmt:message key="ticket.dateAndTimeDepart"/></th>
            <th><fmt:message key="ticket.carriage"/></th>
            <th><fmt:message key="ticket.carriageNumber"/></th>
            <th><fmt:message key="ticket.place"/></th>
            <th><fmt:message key="ticket.startStation"/></th>
            <th><fmt:message key="ticket.finalStation"/></th>
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
