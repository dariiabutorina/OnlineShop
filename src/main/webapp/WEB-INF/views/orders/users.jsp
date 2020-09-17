<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Top Store | Orders</title>
</head>
<style>
    .btn-light {
        font-family: 'Montserrat', sans-serif;
        text-transform: uppercase;
        width: 200px;
    }

    .btn-dark {
        font-family: 'Montserrat', sans-serif;
        text-transform: uppercase;
        width: 200px;
    }
</style>
<body>
<%@include file="../header.jsp" %>
<br>
<div class="container" align="center" style="text-align: center;">
    <h2> User's Orders </h2>
    <br>
    <table class="table table-hover" style="width: 1200px;
        text-align: center;
        background: rgba(0, 0, 0, 0.5);
        font-family: 'Montserrat', sans-serif;
        text-transform: uppercase;
        font-size: x-large;
        color: white">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Order ID</th>
            <th scope="col">User ID</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${orders}">
            <tr>
                <th scope="row" style="padding-top: 20px">
                    <c:out value="${order.id}"/>
                </th>
                <td style="text-align: center; padding-top: 20px">
                    <c:out value="${order.userId}"/>
                </td>
                <td style="text-align: center">
                    <a href="${pageContext.request.contextPath}/order/details?id=${order.id}"
                       class="btn btn-light"> Details </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/home" class="btn btn-dark"> Go Back </a>
</div>
</body>
</html>
