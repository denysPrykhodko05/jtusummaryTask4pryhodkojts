
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:setLocale value="ru" />
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="reg.title"/></title>
    <script src="../javaScript/ValidationRegistrationPage.js"></script>
</head>
<body>
<body><a href="/"><fmt:message key="login.home"/></a>
<c:if test = "${requestScope.error_bool == true}">
<p style="color:red"><c:out value = "${requestScope.error}"/><p>
    </c:if>
<form name="RegForm" method="post" onsubmit="return validateForm('RegForm','login','password','confirmPassword')">
    E-mail<label><input type="email" name="email" value="${requestScope.email}"></label><br>
    <fmt:message key="login.login"/><label><input type="text" name="login" value="${requestScope.login}"></label><br>
    <fmt:message key="login.password"/><label><input type="password" name="password" value="${requestScope.password}"></label><br>
    <fmt:message key="reg.confirmPassword"/>:<label><input type="password" name="confirmPassword" value="${requestScope.confirmPassword}"></label><br>
    <input type="submit" name="Ok" value="<fmt:message key="reg.title"/>"><br>
</form>
</body>
</html>
