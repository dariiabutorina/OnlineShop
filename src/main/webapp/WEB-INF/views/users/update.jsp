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
    <form method="post" action="${pageContext.request.contextPath}/user/update/name"
          style="font-family: 'Montserrat', sans-serif;
          text-transform: uppercase;
          font-size: x-large">
        <label for="userName"> Name: </label>
        <div class="form-group">
            <input type="text" class="form-control form-control-lg" id="userName"
                   name="name" required placeholder="Your name" width="450 px">
            <input type="hidden" name="upd_user_id" value="${upd_user_id}">
        </div>
        <br>
        <button type="submit" class="btn btn-dark"> Save Changes</button>
        <a href="${pageContext.request.contextPath}/home" class="btn btn-dark"> Go Back </a>
    </form>
</div>
</body>
</html>
