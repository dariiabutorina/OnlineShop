<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Top Store | Oops!</title>
</head>
<style>
    .btn-dark {
        font-family: 'Montserrat', sans-serif;
        text-transform: uppercase;
        width: 200px;
    }
</style>
<%@include file="../header.jsp" %>
<body style="text-align: center;
font-family: 'Montserrat', sans-serif;
font-min-size: x-large;
text-transform: uppercase;">
<br>
<br>
<h2>Oops!</h2>
<br>
<h2>Something went wrong.</h2>
<br>
<a href="${pageContext.request.contextPath}/home" class="btn btn-dark"> Go Back </a>
</body>
</html>
