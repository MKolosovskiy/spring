<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>Add Product</title>
</head>
<body>
<center>
    <form action="<spring:url value="/admin/product/add"/>" method="post">
        ${addProductMessage}
        <br>
        <br>
        Name:<br>
        <input type="text" name="name" value="${name}" required/>
        <br>
        Description:<br>
        <input type="text" name="description" value="${description}" required/>
        <br>
        Price:<br>
        <input type="number" step="0.01" name="price" value="${price}" required/>
        <br>
        <br>
        <input type="submit" value="Add"/>
    </form>
</center>
</body>
</html>