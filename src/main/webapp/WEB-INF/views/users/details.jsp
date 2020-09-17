<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Top Store | Account</title>
</head>
<style>
    .btn-light {
        font-family: 'Montserrat', sans-serif;
        text-transform: uppercase;
        width: 200px;
    }

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
    <h2> Account Details </h2>
    <br>
    <table class="table table-hover" style="width: 1200px;
        text-align: center;
        background: rgba(0, 0, 0, 0.5);
        font-family: 'Montserrat', sans-serif;
        font-size: x-large;
        color: white">
        <thead class="thead-dark" style="text-transform: uppercase">
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Login</th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row" style="padding-top: 20px">
                <c:out value="${user.id}"/>
            </th>
            <td style="text-align: center; text-transform: uppercase; padding-top: 20px">
                <c:out value="${user.name}"/>
            </td>
            <td style="text-align: center; padding-top: 20px">
                <c:out value="${user.login}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/user/update?id=${user.id}"
                   class="btn btn-light">Edit</a>
            </td>
        </tr>
        </tbody>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/home" class="btn btn-dark"> Go Back </a>
    <a href="${pageContext.request.contextPath}/logout" class="btn btn-dark"> Sign Out </a>
</div>
</body>
</html>
