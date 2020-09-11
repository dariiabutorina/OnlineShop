<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType = "text/html;charset=UTF-8" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<%@include file="../header.jsp"%>
<h1> Order Details </h1>
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
        </tr>
    </c:forEach>
</table>
<br>
<h5> Total: ${sum}</h5>
<br>
<a href = "${pageContext.request.contextPath}/welcome"> Main Menu </a>
</body>
</html>
