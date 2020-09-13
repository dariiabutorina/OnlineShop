<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType = "text/html;charset=UTF-8" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<%@include file="../header.jsp"%>
<br>
<div class="container" align="center" style="text-align: center">
<h2> All Users </h2>
<br>
    <table class="table table-hover" style="width: 1200px; text-align: center">
    <thead class="thead-light">
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Name</th>
        <th scope="col">Login</th>
        <th scope="col"></th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
        <c:forEach var="user" items="${users}">
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
            <td style="text-align: center">
                <a href="${pageContext.request.contextPath}/user/details?id=${user.id}"
                   class="btn btn-light">Details</a>
            </td>
            <td style="text-align: center">
                <a href="${pageContext.request.contextPath}/user/delete?id=${user.id}"
                   class="btn btn-light">Delete</a>
            </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<a href="${pageContext.request.contextPath}/" class="btn btn-light"> Go Back </a>
</div>
</body>
</html>
