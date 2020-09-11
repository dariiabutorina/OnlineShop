<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType = "text/html;charset=UTF-8" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<%@include file="../header.jsp"%>
<h1> All Orders </h1>
<table border = "1">
    <tr>
        <th>ID</th>
        <th>User ID</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>
                <c:out value="${order.id}"/>
            </td>
            <td>
                <c:out value="${order.userId}"/>
            </td>
            <td>
                <a href = "${pageContext.request.contextPath}/orders/details?id=${order.id}"> Details </a>
            </td>
            <td>
                <a href = "${pageContext.request.contextPath}/orders/delete?id=${order.id}"> Delete </a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<br>
<a href = "${pageContext.request.contextPath}/welcome"> Main Menu </a>
</body>
</html>
