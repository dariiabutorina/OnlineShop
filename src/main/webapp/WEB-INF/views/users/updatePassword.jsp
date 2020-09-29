<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Top Store | Edit Account</title>
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
    <h4>Please, provide the required information below.</h4>
    <br>
    <h6 style="color: #dc143c"> ${message} </h6>
    <form method="post" action="${pageContext.request.contextPath}/user/update/password"
          style="font-family: 'Montserrat', sans-serif;
          text-transform: uppercase;
          font-size: x-large">
        <label for="oldPassword"> Old Password: </label>
        <div class="form-group">
            <input type="password" class="form-control form-control-lg" id="oldPassword"
                   name="old-password" required placeholder="Your password" width="450 px">
        </div>
        <label for="newPassword"> New Password: </label>
        <div class="form-group">
            <input type="password" class="form-control form-control-lg" id="newPassword"
                   name="new-password" required placeholder="Your password" width="450 px">
        </div>
        <label for="repeatPassword"> Repeat the password: </label>
        <div class="form-group">
            <input type="password" class="form-control form-control-lg" id="repeatPassword"
                   name="password-repeat" required placeholder="Your password" width="450 px">
        </div>
        <br>
        <button type="submit" class="btn btn-dark"> Save Changes</button>
        <a href="${pageContext.request.contextPath}/home" class="btn btn-dark"> Go Back </a>
    </form>
</div>
</body>
</html>
