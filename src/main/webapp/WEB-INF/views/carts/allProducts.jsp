<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Shopping Cart</title>
</head>
<body>
<%@include file="../header.jsp"%>
<h1> All Products </h1>
    <table border = "1">
        <form method = "post" action = "${pageContext.request.contextPath}/order">
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
                    <a href = "${pageContext.request.contextPath}/shopping-cart/delete?id=${product.id}"> Delete </a>
                </td>
            </tr>
        </c:forEach>
    </table>
<br>
<button type = "submit"> Complete order </button>
</form>
<br>
<br>
<a href = "${pageContext.request.contextPath}/welcome"> Main Menu </a>
</body>
</html>
