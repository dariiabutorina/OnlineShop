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
    }

    .form-control {
        width: 1000%;
        font-family: 'Montserrat', sans-serif;
        text-transform: uppercase;
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
            <th scope="row">
                <c:out value="${user.id}"/>
            </th>
            <td style="text-align: center">
                <c:out value="${user.name}"/>
            </td>
            <td style="text-align: center">
                <c:out value="${user.login}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/user/update?id=${user.id}"
                   class="btn btn-light">Edit</a>
            </td>
            <td>
                <form method="get" action="${pageContext.request.contextPath}/user/role">
                    <div class="form-row align-items-center">
                        <input type="hidden" name="id" value="${user.id}">
                        <div class="col-auto my-1">
                            <select id="roles" name="test" class="form-control">
                                <option selected>Select Role...</option>
                                <option value="USER">User</option>
                                <option value="ADMIN">Admin</option>
                            </select>
                        </div>
                        <div class="col-auto my-1">
                            <button type="submit" class="btn btn-light">Add Role</button>
                        </div>
                    </div>
                </form>
            </td>
        </tr>
        </tbody>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/" class="btn btn-light"> Go Back </a>
    <a href="${pageContext.request.contextPath}/logout" class="btn btn-light"> Sign Out </a>
</div>
</body>
</html>
