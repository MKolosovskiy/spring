<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>Add User</title>
</head>
<body>
<center>
    <form action="<spring:url value="/user/order"/>" method="post">
        <br>
        <br>
        First name:<br>
        <input type="text" name="firstName" value="${firstName}" required/>
        <br>
        Last name:<br>
        <input type="text" name="lastName" value="${lastName}" required/>
        <br>
        Email:<br>
        <input type="email" name="email" value="${email}" required/>
        <br>
        Address:<br>
        <input type="text" name="address" value="${address}" required/>
        <br>
        <br>
        <input type="submit" value="Confirm Order"/>
    </form>
</center>
</body>
</html>
