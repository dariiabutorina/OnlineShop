<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<%@include file="header.jsp"%>
    <br>
<div class="container" align="center" style="text-align: center">
    <h1>Hello!</h1>
    <br>
    <h2>Please, provide the required information below.</h2>
    <br>
    <h4 style = "color: #dc143c"> ${message} </h4>
    <form method = "post" action = "${pageContext.request.contextPath}/registration">
        <label for="userName"> Name: </label>
        <div class="form-group">
            <input type="text" class="form-control" id="userName"
                   name = "name" required placeholder="Your name" width="450 px">
        </div>
        <label for="userLogin"> Login: </label>
        <div class="form-group">
            <input type="text" class="form-control" id="userLogin"
                   name = "login" required placeholder="Your login" width="450 px">
        </div>
        <label for="userPassword"> Password: </label>
        <div class="form-group">
            <input type="password" class="form-control" id="userPassword"
                   name = "password" required placeholder="Your password" width="450 px">
        </div>
        <label for="repeatPassword"> Repeat the password: </label>
        <div class="form-group">
            <input type="password" class="form-control" id="repeatPassword"
                   name = "password-repeat" required placeholder="Your password" width="450 px">
        </div>
        <br>
        <button type="submit" class="btn btn-light"> Sing up </button>
        <a href="${pageContext.request.contextPath}/" class="btn btn-light"> Go Back </a>
    </form>
</div>
</body>
</html>
