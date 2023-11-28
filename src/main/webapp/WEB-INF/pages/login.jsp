<%--
  Created by IntelliJ IDEA.
  User: developerjeyhun
  Date: 05.10.23
  Time: 13:10
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <form action="${pageContext.request.contextPath}/login" method="post">

        <div>Username : <input type="text", name="username"/></div></br>

        <div>Password : <input type="password", name="password"/></div></br>

        <c:if test="${not empty msg}">
            <span style="color: red; font-size: 16px">${msg}</span>
        </c:if>

        <div><input type="submit" value="Log In"/>&nbsp;<input type="submit" value="Clear"/></div>
    </form>
</div>
</body>
</html>
