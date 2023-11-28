<%--
  Created by IntelliJ IDEA.
  User: developerjeyhun
  Date: 03.10.23
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        table, th, td {
            border: 1px solid;
        }
    </style>
</head>
<body>
    <table >
        <th>
            <td>Name</td>
            <td>Surname</td>
            <td>Date of Birth</td>
            <td>Address</td>
            <td>Phone</td>
        </th>
        <c:forEach items="${customers}" var="customer">
            <tr>
                <td>${customer.id}</td>
                <td>${customer.name}</td>
                <td>${customer.surname}</td>
                <td>${customer.dob}</td>
                <td>${customer.address}</td>
                <td>${customer.phone}</td>
            </tr>
        </c:forEach>
    </table>
    <a style="text-decoration: none" href="${pageContext.request.contextPath}/app/newCustomer"><button>New Customer</button></a>
    <form action="${pageContext.request.contextPath}/logout" method="post">
        <input type="submit" value="Logout">
    </form>
</body>
</html>
