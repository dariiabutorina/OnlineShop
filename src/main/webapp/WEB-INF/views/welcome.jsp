<%@ page contentType = "text/html;charset=UTF-8" %>
<html>
<head>
    <title>Top Store - Best Cosmetics</title>
</head>
<body>
    <h5>${time}</h5>
    <h1>Hello, makeup lover!</h1>
    <h2> Select and click on the button for redirection. </h2>
    <a href = "${pageContext.request.contextPath}/users/all"> All Users </a>
    <a href = "${pageContext.request.contextPath}/products/all"> All Products </a>
    <a href = "${pageContext.request.contextPath}/shopping-cart"> Shopping Cart </a>
</body>
</html>
