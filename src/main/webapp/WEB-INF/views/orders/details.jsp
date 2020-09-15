<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Top Store | Order</title>
</head>
<style>
    .btn-light {
        font-family: 'Montserrat', sans-serif;
        text-transform: uppercase;
    }
</style>
<body>
<%@include file="../header.jsp" %>
<br>
<div class="container" align="center" style="text-align: center">
    <h2> Order's Details </h2>
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
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Price</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${products}">
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
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <h4 style="text-align: center"> Total: ${sum}</h4>
    <br>
    <a href="${pageContext.request.contextPath}/" class="btn btn-light"> Go Back </a>
</div>
</body>
</html>
