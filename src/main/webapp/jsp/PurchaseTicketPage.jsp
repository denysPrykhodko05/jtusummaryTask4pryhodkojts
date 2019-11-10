<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:setLocale value="ru" />
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="purchase.title"/></title>
</head>
<body>
<body><a href="/"><fmt:message key="login.home"/></a><br><a href="/logout"><fmt:message key="index.Logout"/></a><br>
<h2>${requestScope.from} - ${requestScope.to}</h2><br>


    <c:if test="${requestScope.errorFullCarriage ==true}">
    <p style="color: red"><fmt:message key="purchase.errorFullCarriage"/></p>
</c:if>

<form method="post" action="<c:url value="/purchaseTicket"/>">
    <input type="number" hidden name="id" value="${requestScope.trainId}"/>
    <input type="hidden" id="from" name="from" value="${requestScope.startStationName}">
    <input type="hidden" id="to" name="to" value="${requestScope.finalStationName}">
    <input type="hidden" id="price" name="price" value="${requestScope.priceForCompartment}">
    <input type="hidden" id="arrive_time" name="arrive_time" value="${requestScope.arrive_time}">
    <input type="hidden" id="depart_time" name="depart_time" value="${requestScope.depart_time}">
    <c:if test="${requestScope.compartment!=null}">
        <input type="hidden" id="compartment" name="compartment" value="${requestScope.compartment}">
    </c:if>
    <c:if test="${requestScope.common!=null}">
        <input type="hidden" id="common" name="common" value="${requestScope.common}">
    </c:if>
    <c:if test="${requestScope.economy_class!=null}">
        <input type="hidden" id="economy_class" name="economy_class" value="${requestScope.economy_class}">
    </c:if>

    <fmt:message key="purchase.selectCarriage"/>:
    <c:if test="${requestScope.amount_compartment_carriages!=null}">
        <select id="select" name="compartment_carriage" data-value="${requestScope.amount_compartment_carriages}">
            <script type="text/javascript" src="../javaScript/addOptionInSelect"></script>
        </select>
    </c:if>

    <c:if test="${requestScope.amount_common_carriages!=null}">
        <select id="select" data-value="${requestScope.amount_common_carriages}" name="common_carriage">
            <script type="text/javascript" src="../javaScript/addOptionInSelect"></script>
        </select>
    </c:if>

    <c:if test="${requestScope.amount_economy_carriages!=null}">
        <select id="select" data-value="${requestScope.amount_economy_carriages}" name="economy_carriage">
            <script type="text/javascript" src="../javaScript/addOptionInSelect"></script>
        </select>
    </c:if><br>
    <input type="submit" name="Ok" value="Ok">
</form>
</body>
</html>
