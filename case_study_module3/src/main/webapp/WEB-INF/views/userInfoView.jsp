<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/19/2022
  Time: 8:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Hello: ${loginedUser.userName}</h3>

User Name: <b>${loginedUser.userName}</b>
<br />
Gender: ${loginedUser.gender } <br />
</body>
</html>
