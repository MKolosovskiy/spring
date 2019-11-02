<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>All Users</title>
</head>
<body>
<center>
    <form action="<spring:url value="/admin/user/all"/>" method="get">
        <table border=1>
            <tr>
                <th> Email </th>
                <th> Password </th>
                <th> Edit </th>
                <th> Delete </th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.email}</td>
                    <td>${user.password}</td>
                    <td>
                        <button><a href='<c:out value="/admin/user/edit?id=${user.id}"/>'> Edit </a></button>
                    </td>
                    <td>
                        <button><a href='<c:out value="/admin/user/remove?id=${user.id}"/>'> Delete </a></button>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <button><a href='<c:out value="/admin/user/add"/>'> Add User </a></button>
        <button><a href='<c:out value="/admin/product/all"/>'> All Products</a></button>
    </form>
</center>
</body>
</html>