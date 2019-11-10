
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="login.label"/></title>
    <script src="../javaScript/ValidationLoginPage.js"></script>
</head>
<body><a href="/"><fmt:message key="login.home"/></a>

<c:if test = "${requestScope.error_bool == true}">
<p style="color:red"><c:out value = "${requestScope.error}"/><p>
    </c:if>
        <form name="LoginForm" method="post" action="<c:url value="/login"/>" onsubmit="return validateForm('LoginForm','login','password')">
    <fmt:message key="login.login"/><input type="text" name="login" value="${requestScope.login}"><br>
    <fmt:message key="login.password"/><input type="password" name="password" value="${requestScope.password}"><br>
            <input type="submit" login="Ok" value="Ok"><br>
            <a href="/registration"><fmt:message key="login.registration"/></a>
        </form>
</body>
</html>
