<%@ page contentType = "text/html;charset=UTF-8" %>
<html>
<head>
    <title>Top Store - Best Cosmetics</title>
</head>
<body>
<%@include file="header.jsp"%>
    <h5>${time}</h5>
    <h1>Hello, makeup lover!</h1>
    <h2> Select and click on the button for redirection. </h2>
    <br>
    <a href = "${pageContext.request.contextPath}/users/all"> All Users </a>
    <br>
    <a href = "${pageContext.request.contextPath}/products/all"> All Products </a>
    <br>
    <a href = "${pageContext.request.contextPath}/products/all/admin"> All Products - Admin </a>
    <br>
    <a href = "${pageContext.request.contextPath}/shopping-cart"> Shopping Cart </a>
    <br>
    <a href = "${pageContext.request.contextPath}/orders/all"> All Orders - Admin </a>
    <br>
    <a href = "${pageContext.request.contextPath}/user/orders"> User's Orders </a>
</body>
</html>
