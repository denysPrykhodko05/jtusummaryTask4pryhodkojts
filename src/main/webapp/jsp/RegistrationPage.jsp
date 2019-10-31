<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 27.10.2019
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <script src="../javaScript/ValidationRegistrationPage.js"></script>
</head>
<body>
<c:if test = "${requestScope.error_bool == true}">
<p style="color:red"><c:out value = "${requestScope.error}"/><p>
    </c:if>
<form name="RegForm" method="post" onsubmit="return validateForm('RegForm','login','password','confirmPassword')">
    Login<label><input type="text" name="login" value="${requestScope.login}"></label><br>
    Password<label><input type="password" name="password" value="${requestScope.password}"></label><br>
    Confirm password<label><input type="password" name="confirmPassword" value="${requestScope.confirmPassword}"></label><br>
    <input type="submit" name="Ok" value="Registration"><br>
</form>
</body>
</html>
