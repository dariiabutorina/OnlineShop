<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title> Top Store | Edit Product</title>
</head>
<style>
    .btn-light {
        font-family: 'Montserrat', sans-serif;
        text-transform: uppercase;
    }
</style>
<body>
<%@include file="../header.jsp" %>
<div class="container" align="center" style="text-align: center">
    <br>
    <h2>Please, provide required information below.</h2>
    <h4 style="color: #dc143c"> ${message} </h4>
    <form method="post" action="${pageContext.request.contextPath}/product/update"
          style="font-family: 'Montserrat', sans-serif;
          text-transform: uppercase;
          font-size: x-large">
        <label for="productName"> Name: </label>
        <div class="form-group">
            <input type="text" class="form-control form-control-lg" id="productName"
                   name="name" required placeholder="New product's name" width="450 px">
        </div>
        <label for="productPrice"> Price: </label>
        <div class="form-group">
            <input type="number" class="form-control form-control-lg" id="productPrice"
                   name="price" required placeholder="New product's price" width="450 px">
        </div>
        <br>
        <button type="submit" class="btn btn-light"> Save Changes</button>
        <a href="${pageContext.request.contextPath}/" class="btn btn-light"> Go Back </a>
    </form>
</div>
<br>
</body>
</html>
