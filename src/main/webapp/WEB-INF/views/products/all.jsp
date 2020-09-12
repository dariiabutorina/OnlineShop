<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title> Products </title>
</head>
<body>
<%@include file="../header.jsp"%>
<br>
<div class="container" align="center" style="text-align: center">
<h2> All Products </h2>
<br>
    <table class="table table-hover" style="width: 1200px; text-align: center">
    <thead class="thead-light">
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Name</th>
        <th scope="col">Price</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var = "product" items = "${products}">
        <tr>
            <th scope="row">
                <c:out value="${product.id}"/>
            </th>
            <td style="text-align: center">
                <c:out value="${product.name}"/>
            </td>
            <td style="text-align: center">
                <c:out value="${product.price}"/>
            </td>
            <td style="text-align: center">
                <a href="${pageContext.request.contextPath}/products/buy?id=${product.id}" class="btn btn-light">Buy</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="${pageContext.request.contextPath}/" class="btn btn-light"> Go Back </a>
</div>
</body>
</html>
