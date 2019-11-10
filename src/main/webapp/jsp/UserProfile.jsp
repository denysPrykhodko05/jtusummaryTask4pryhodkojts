<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 03.11.2019
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="ru"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="index.profile"/></title>
    <script src="../javaScript/CountValidation.js"></script>
</head>
<body>
<body><a href="/"><fmt:message key="login.home"/></a><br><a href="/logout"><fmt:message key="index.Logout"/></a>
<h2><fmt:message key="userprofile.hello"/>, ${sessionScope.login}</h2>
<a href="<c:url value="/profile/ticket"/>"><fmt:message key="userprofile.ticket"/></a>
<c:if test="${requestScope.errorProfile!=null}">
    <p style="color: red;">${requestScope.errorProfile}</p><br>
</c:if>

<c:if test="${requestScope.errorMoney!=null}">
    <p>${requestScope.errorMoney}</p>
</c:if>

<div>
    <fmt:message key="userprofile.count"/> : ${sessionScope.count}<br>
    <form name="countForm" method="post" name="countForm" action="<c:url value="/profile"/>" onsubmit="return countValidation('countForm', 'count')">
    <label><fmt:message key="userprofile.fundAccount"/> : <input type="number" name="count"></label><br><input type="submit" name="Ok" value="Ok">
    </form>
</div>

</body>
</html>
