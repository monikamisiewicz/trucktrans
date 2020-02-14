<%--
  Created by IntelliJ IDEA.
  User: monikamisiewicz
  Date: 2020-01-25
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}/users"/>
<html lang="en">

<%@include file="../head.jsp" %>
<body>
<%@include file="../header.jsp" %>

<h1>User list</h1>
<button>
    <a href="${contextPath}"> New user </a>
</button>

<button>
    <a href="/">Back</a>
</button>

<table style="width:100%">
    <thead>
    <tr>
        <th>Id</th>
        <th>Login</th>
        <th>Password</th>
        <th>Email</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.login}</td>
            <td>${user.password}</td>
            <td>${user.email}</td>
            <td>
                <button><a href="${contextPath}/edit/${user.id}"> Edit </a></button>
                <button><a href="${contextPath}/delete/${user.id}"> Delete </a></button>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>

<script src="http://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
<script src="https://unpkg.com/@coreui/coreui/dist/js/coreui.min.js"></script>

<%@include file="../footer.jsp" %>
</body>
</html>
