<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>All Products</title>
</head>
<body>
<center>
    <form action="<spring:url value="/admin/product/all"/>" method="get">
        <table border=1>
            <tr>
                <th> Name </th>
                <th> Description </th>
                <th> Price </th>
                <th> Edit </th>
                <th> Delete </th>
            </tr>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td>${product.name}</td>
                    <td>${product.description}</td>
                    <td>${product.price}</td>
                    <td>
                        <button><a href='<c:out value="/admin/product/edit?id=${product.id}"/>'> Edit </a></button>
                    </td>
                    <td>
                        <button><a href='<c:out value="/admin/product/remove?id=${product.id}"/>'> Delete </a></button>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <button><a href='<c:out value="/admin/product/add"/>'> Add Product </a></button>
        <button><a href='<c:out value="/admin/user/all"/>'> All Users</a></button>
    </form>
</center>
</body>
</html>
