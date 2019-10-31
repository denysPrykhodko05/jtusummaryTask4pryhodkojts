<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 27.10.2019
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <script src="../javaScript/ValidationLoginPage.js"></script>
</head>
<body>
<c:if test = "${requestScope.error_bool == true}">
<p style="color:red"><c:out value = "${requestScope.error}"/><p>
    </c:if>
        <form name="LoginForm" method="post" onsubmit="return validateForm('LoginForm','login','password')">
            Login<input type="text" name="login" value="${requestScope.login}"><br>
            Password<input type="password" name="password" value="${requestScope.password}"><br>
            <input type="submit" login="Ok" value="Ok"><br>
            <a href="/registration">Registration</a>
        </form>
</body>
</html>
