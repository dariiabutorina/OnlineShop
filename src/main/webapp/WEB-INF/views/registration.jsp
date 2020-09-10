<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>

<body>
<%@include file="header.jsp"%>
    <br>
    <h1>Hello!</h1>
    <h2>Please, provide required information below.</h2>
    <h4 style = "color: #dc143c"> ${message} </h4>
    <form method = "post" action = "${pageContext.request.contextPath}/registration">
        <table border = "1">
            <tr>
                <th> Name: </th>
                <th> Login: </th>
                <th> Password: </th>
                <th> Repeat the password: </th>
            </tr>
            <tr>
                <th> <input type = "text" name = "name" required> </th>
                <th> <input type = "text" name = "login" required> </th>
                <th> <input type = "password" name = "password" required> </th>
                <th> <input type = "password" name = "password-repeat" required> </th>
            </tr>
        </table>
    <br>
    <button type = "submit"> Sing up </button>
    </form>
    <br>
    <br>
    <a href = "${pageContext.request.contextPath}/"> Go Back </a>
</body>
</html>
