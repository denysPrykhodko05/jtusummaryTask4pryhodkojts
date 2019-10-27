<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 27.10.2019
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <script src="../javaScript/ValidationLoginPage.js"></script>
</head>
<body>
        <form login="LoginForm" method="post" onsubmit="return validateForm('LoginForm','login','password')">
            Login<input type="text" login="login"><br>
            Password<input type="password" login="password"><br>
            <input type="submit" login="Ok" value="Ok"><br>
            <a href="RegistrationPage.jsp">Regestration</a>
        </form>
</body>
</html>
