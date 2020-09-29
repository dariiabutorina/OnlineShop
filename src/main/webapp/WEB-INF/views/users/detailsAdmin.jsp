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

    .mr-sm-2 {
        font-family: 'Montserrat', sans-serif;
        text-transform: uppercase;
        font-size: x-small;
        width: 200px;
        align-items: center;
        align-content: center;
        text-align: center;
        vertical-align: center;
    }

    td {
        align-items: center;
        align-content: center;
        text-align: center;
        vertical-align: center;
    }

    option {
        background: #fdfdfd;
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
            <th scope="row" style="padding-top: 30px">
                <c:out value="${user.id}"/>
            </th>
            <td style="text-align: center; padding-top: 30px; text-transform: uppercase">
                <c:out value="${user.name}"/>
            </td>
            <td style="text-align: center; padding-top: 30px">
                <c:out value="${user.login}"/>
            </td>
            <form method="get" action="${pageContext.request.contextPath}/user/role">
                <div class="form-row align-items-center">
                    <td>
                        <input type="hidden" name="id" value="${user.id}">
                        <div class="col-auto my-1" style="padding-bottom: 15px">
                            <select id="roles" name="roleAdd" class="custom-select my-1 mr-sm-2" style="height: 46px">
                                <option selected style="align-content: center">Click to select...</option>
                                <option value="USER" style="text-align: center">User</option>
                                <option value="ADMIN" style="text-align: center">Admin</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="col-auto my-1" style="padding-top: 7px">
                            <button type="submit" class="btn btn-light">Add Role</button>
                        </div>
                    </td>
                </div>
            </form>
        </tr>
        </tbody>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/home" class="btn btn-dark"> Go Back </a>
</div>
</body>
</html>
