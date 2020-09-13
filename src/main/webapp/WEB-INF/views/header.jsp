<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1, shrink-to-fit=no" name="viewport">
    <link crossorigin="anonymous" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script crossorigin="anonymous"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script crossorigin="anonymous"
            integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
            src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Chivo:300,700|Playfair+Display:700i" rel="stylesheet">
    <style>
        body {
            background-color: white;
            font-family: 'Lora', serif;
            font-weight: 500;
        }
        .header {
            padding: 4%;
            background: url("https://i.imgur.com/kK0rr1z.png") no-repeat center #efefef;
            font-family: 'Chivo', sans-serif;
            font-weight: 300;
            font-min-size: xx-large;
        }
        h1 {
            font-family: 'Playfair Display', serif;
            font-weight: 700;
            font-style: italic;
            text-transform: uppercase;
            font-min-size: xx-large;
        }
        h2, h3, h4, h5, h6 {
            font-family: 'Montserrat', sans-serif;
            font-weight: 300;
            font-min-size: xx-large;
        }
        html, body {
            margin: 0;
            padding: 0;
        }
        .navbar {
            padding: 2%;
        }
    </style>
</head>
<body>
<div class="header" style="text-align: center">
    <h1>TOP STORE</h1>
    <h4>High-quality cosmetics must-haves</h4>
</div>
<div style="font-family: 'Montserrat', sans-serif">
<div class="navbar navbar-expand-lg navbar-light bg-light">
    <div style="text-transform: uppercase">
    <ul class="navbar-nav" style="position: center; color: white">
        <li class="nav-item active">
            <a class="nav-link" href="${pageContext.request.contextPath}/">Home</a>
        </li>
        <li><h6>|</h6></li>
        <li class="nav-item active">
            <a class="nav-link" href="${pageContext.request.contextPath}/products/all">Products</a>
        </li>
        <li><h6>|</h6></li>
        <li class="nav-item active">
            <a class="nav-link" href="${pageContext.request.contextPath}/products/all/admin">Products (Admin)</a>
        </li>
        <li><h6>|</h6></li>
        <li class="nav-item active">
            <a class="nav-link" href="${pageContext.request.contextPath}/users/all">Users (Admin)</a>
        </li>
        <li><h6>|</h6></li>
        <li class="nav-item active">
            <a class="nav-link" href="${pageContext.request.contextPath}/orders/all">Orders (Admin)</a>
        </li>
        <li><h6>|</h6></li>
        <li class="nav-item active">
            <a class="nav-link" href="${pageContext.request.contextPath}/user/orders">My Orders</a>
        </li>
        <li><h6>|</h6></li>
        <li class="nav-item active">
            <a class="nav-link" href="${pageContext.request.contextPath}/shopping-cart">Shopping cart</a>
        </li>
        <li class="nav-item active" style="position: absolute; right: 290px">
            <a class="nav-link" href="${pageContext.request.contextPath}/registration">Sign Up</a>
        </li>
        <li><h6>|</h6></li>
        <li class="nav-item active" style="position: absolute; right: 200px;">
            <a class="nav-link" href="${pageContext.request.contextPath}/authorization">Sign In</a>
        </li>
        <li><h6>|</h6></li>
        <li class="nav-item active" style="position: absolute; right: 10px">
            <a class="nav-link" href="${pageContext.request.contextPath}/user/details?id=1">Account Details</a>
        </li>
    </ul>
    </div>
    </div>
</div>
</body>
</html>
