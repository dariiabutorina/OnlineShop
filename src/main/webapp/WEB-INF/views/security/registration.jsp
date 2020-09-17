<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Top Store | Registration</title>
</head>
<style>
    .btn-dark {
        font-family: 'Montserrat', sans-serif;
        text-transform: uppercase;
        width: 200px;
    }
</style>
<body>
<%@include file="../header.jsp" %>
<br>
<div class="container" align="center" style="text-align: center">
    <h2>Hello!</h2>
    <br>
    <h3>Please, provide the required information below.</h3>
    <br>
    <h3 style="color: #dc143c"> ${message} </h3>
    <form method="post" action="${pageContext.request.contextPath}/registration"
          style="font-family: 'Montserrat', sans-serif;
        text-transform: uppercase;
        font-size: x-large">
        <label for="userName"> Name: </label>
        <div class="form-group">
            <input type="text" class="form-control form-control-lg" id="userName"
                   name="name" required placeholder="Your name" width="450 px">
        </div>
        <label for="userLogin"> Login: </label>
        <div class="form-group">
            <input type="text" class="form-control form-control-lg" id="userLogin"
                   name="login" required placeholder="Your login" width="450 px">
        </div>
        <label for="userPassword"> Password: </label>
        <div class="form-group">
            <input type="password" class="form-control form-control-lg" id="userPassword"
                   name="password" required placeholder="Your password" width="450 px">
        </div>
        <label for="repeatPassword"> Repeat the password: </label>
        <div class="form-group">
            <input type="password" class="form-control form-control-lg" id="repeatPassword"
                   name="password-repeat" required placeholder="Your password" width="450 px">
        </div>
        <br>
        <button type="submit" class="btn btn-dark"> Sing up</button>
        <a href="${pageContext.request.contextPath}/home" class="btn btn-dark"> Go Back </a>
    </form>
</div>
</body>
</html>
