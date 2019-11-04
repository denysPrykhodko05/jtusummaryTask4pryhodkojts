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
<h2>Hello, ${sessionScope.login}</h2>
<div>
    Count: ${sessionScope.count}<br>
    <form name="countForm" method="post" name="countForm" action="<c:url value="/profile"/>" onsubmit="return countValidation('countForm', 'count')">
    <label>Fund account: <input type="number" name="count"></label><input type="submit" name="Ok" value="Ok">
    </form>
    <div>${requestScope.error}<div>
</div>

</body>
</html>
