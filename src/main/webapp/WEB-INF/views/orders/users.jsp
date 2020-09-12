<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType = "text/html;charset=UTF-8" %>
<html>
<head>
    <title>User's Orders</title>
</head>
<body>
<%@include file="../header.jsp"%><br>
<div class="container" align="center" style="text-align: center">
<h2> User's Orders </h2>
<br>
    <table class="table table-hover" style="width: 1200px; text-align: center">
    <thead class="thead-light">
    <tr>
        <th scope="col">Order ID</th>
        <th scope="col">User ID</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${orders}">
        <tr>
            <th scope="row">
                <c:out value="${order.id}"/>
            </th>
            <td style="text-align: center">
                <c:out value="${order.userId}"/>
            </td>
            <td style="text-align: center">
                <c:out value="${product.price}"/>
            </td>
            <td style="text-align: center">
                <a href="${pageContext.request.contextPath}/orders/details?id=${order.id}"
                   class="btn btn-light"> Details </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="${pageContext.request.contextPath}/" class="btn btn-light"> Go Back </a>
</div>
</body>
</html>
