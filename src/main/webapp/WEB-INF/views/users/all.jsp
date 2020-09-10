<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType = "text/html;charset=UTF-8" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<%@include file="../header.jsp"%>
    <h1> All Users </h1>
    <table border = "1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Login</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>
                    <c:out value="${user.id}"/>
                </td>
                <td>
                    <c:out value="${user.name}"/>
                </td>
                <td>
                    <c:out value="${user.login}"/>
                </td>
                <td>
                    <a href = "${pageContext.request.contextPath}/users/delete?id=${user.id}"> Delete </a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <br>
    <a href = "${pageContext.request.contextPath}/welcome"> Main Menu </a>
</body>
</html>
