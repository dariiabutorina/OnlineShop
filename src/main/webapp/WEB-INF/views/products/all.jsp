<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title> Products </title>
</head>
<body>
<%@include file="../header.jsp"%>
<h1> All Products </h1>
<table border = "1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
    </tr>
    <c:forEach var = "product" items = "${products}">
        <tr>
            <td>
                <c:out value="${product.id}"/>
            </td>
            <td>
                <c:out value="${product.name}"/>
            </td>
            <td>
                <c:out value="${product.price}"/>
            </td>
            <td>
                <a href = "${pageContext.request.contextPath}/products/buy?id=${product.id}"> Buy </a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<a href = "${pageContext.request.contextPath}/products/add"> Add New Product </a>
<br>
<a href = "${pageContext.request.contextPath}/welcome"> Main Menu </a>
</body>
</html>
