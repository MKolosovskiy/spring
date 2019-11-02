<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Users</title>
</head>
<body>
<center>
    <form action="<spring:url value="/user/code/send"/>" method="post">
        Code:<br>
        <input type="text" name="sendCode" >
        <input type="submit" value="send">
    </form>
</center>
</body>
</html>