<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Shopping Cart</title>
</head>
<body>
<%@include file="../header.jsp"%>
<div class="container" align="center" style="text-align: center">
<h2> My Shopping Cart </h2>
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
                <a href="${pageContext.request.contextPath}/shopping-cart/delete?id=${product.id}"
                   class="btn btn-light">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<a href="${pageContext.request.contextPath}/orders/complete?cartId=${cartId}"
   class="btn btn-light">Create order</a>
<a href="${pageContext.request.contextPath}/" class="btn btn-light"> Go Back </a>
</div>
</body>
</html>
