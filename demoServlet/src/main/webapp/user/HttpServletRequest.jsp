<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/10/2022
  Time: 8:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>XIN CHÀO</h1>
<% int id = Integer.parseInt(request.getParameter("id"));
String name = request.getParameter("name");%>
<p>Id của bạn là <%=id%></p>
<p>Tên của bạn là <%=name%></p>
<a href="logout.jsp">Đăng xuất</a>
</body>
</html>
