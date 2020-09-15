<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Top Store | Log In</title>
</head>
<style>
    .btn-light {
        font-family: 'Montserrat', sans-serif;
        text-transform: uppercase;
    }
</style>
<body style="text-align: center; align-items: center">
<%@include file="header.jsp" %>
<br>
<h2>Hello!</h2>
<br>
<h3>Please, provide the required information below.</h3>
<br>
<h3 style="color: #dc143c"> ${message} </h3>
<br>
<form method="post" action="${pageContext.request.contextPath}/login"
      style="font-family: 'Montserrat', sans-serif;
        text-transform: uppercase;
        font-size: x-large">
    <div class="container" align="center">
        <label for="userLogin"> Login: </label>
        <div class="form-group">
            <input type="text" class="form-control form-control-lg" id="userLogin"
                   name="login" required placeholder="Your login">
        </div>
        <label for="userPassword"> Password: </label>
        <div class="form-group">
            <input type="password" class="form-control form-control-lg" id="userPassword"
                   name="password" required placeholder="Your password">
        </div>
        <br>
        <button type="submit" class="btn btn-light"> Sign in</button>
        <a href="${pageContext.request.contextPath}/" class="btn btn-light"> Go Back </a>
        <a href="${pageContext.request.contextPath}/registration" class="btn btn-light"> Sign Up </a>
    </div>
</form>
</body>
</html>
