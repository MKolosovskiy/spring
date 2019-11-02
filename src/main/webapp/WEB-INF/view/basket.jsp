<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Basket</title>
</head>
<body>
<center>
    ${size}
        <table border=1>
            <tr>
                <th> Name </th>
                <th> Description </th>
                <th> Price </th>
            </tr>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td>${product.name}</td>
                    <td>${product.description}</td>
                    <td>${product.price}</td>
                    <td>
                        <button><a href='<c:out value="/user/basket/product/remove?id=${product.id}"/>'> Delete </a></button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    <br>
        <c:if test="${size > 0}">
            <button><a href='<c:out value="/user/order"/>'> Next </a></button>
        </c:if>
        <c:if test="${size == 0}">
            <button><a href='<c:out value="/user/product/all"/>'> Next </a></button>
        </c:if></center>
</body>
</html>
