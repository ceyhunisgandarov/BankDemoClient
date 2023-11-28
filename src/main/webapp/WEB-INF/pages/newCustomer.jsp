<%--
  Created by IntelliJ IDEA.
  User: developerjeyhun
  Date: 05.10.23
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Customer</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/app/addCustomer" method="post">
        Name : <input type="text" name="name" /> </br>
        Surname : <input type="text" name="surname" /> </br>
        Date of Birth : <input type="date" name="dob" /> </br>
        Address : <input type="text" name="address" /> </br>
        Phone : <input type="text" name="phone" /> </br>
        Serial : <input type="text" name="serial" /> </br>
        Pin : <input type="text" name="pin" /> </br>
        Cif : <input type="text" name="cif" /> </br>
        <input type="submit" value="Add Customer">
    </form>
</body>
</html>
