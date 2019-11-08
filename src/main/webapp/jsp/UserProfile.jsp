<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 03.11.2019
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <script src="../javaScript/CountValidation.js"></script>
</head>
<body>
<body><a href="/">HOME</a><a href="/logout">Logout</a>
<h2>Hello, ${sessionScope.login}</h2>
<a href="<c:url value="/"/>">Home</a><br>
<a href="<c:url value="/profile/ticket"/>">Ticket</a>
<c:if test="${requestScope.errorProfile!=null}">
    <p style="color: red;">${requestScope.errorProfile}</p><br>
</c:if>

<c:if test="${requestScope.errorMoney!=null}">
    <p>${requestScope.errorMoney}</p>
</c:if>

<div>
    Count: ${sessionScope.count}<br>
    <form name="countForm" method="post" name="countForm" action="<c:url value="/profile"/>" onsubmit="return countValidation('countForm', 'count')">
    <label>Fund account: <input type="number" name="count"></label><input type="submit" name="Ok" value="Ok">
    </form>
</div>

</body>
</html>
