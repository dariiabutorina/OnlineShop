<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title> Add Product </title>
</head>
<body>
<br>
<h1>Hello!</h1>
<h2>Please, provide required information below.</h2>
<h4 style = "color: #dc143c"> ${message} </h4>
<form method = "post" action = "${pageContext.request.contextPath}/products/add">
    <table border = "1">
        <tr>
            <th> Name: </th>
            <th> Price: </th>
        </tr>
        <tr>
            <th> <input type = "text" name = "name"> </th>
            <th> <input type = "text" name = "price"> </th>
        </tr>
    </table>
    <br>
    <button type = "submit"> Add Product </button>
</form>
<br>
<br>
<a href = "${pageContext.request.contextPath}/"> Go Back </a>
</body>
</html>
