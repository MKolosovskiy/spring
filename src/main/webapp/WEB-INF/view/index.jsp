<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>Login</title>
</head>
<body>
<center>
    <form action="<spring:url value="/signin"/>" method="post">
        ${message}
        <br>
        <br>
        Email:<br>
        <input type="email" name="email" />
        <br>
        Password:<br>
        <input type="password" name="password" />
        <br>
        <br>
        <input type="submit" value="Login"/>
    </form>
</center>
</body>
</html>
