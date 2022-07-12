<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/10/2022
  Time: 8:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% String name = request.getParameter("name");
    String password = request.getParameter("password");
    String phone = request.getParameter("phone");
    String about = request.getParameter("about");
    String favourites1 = request.getParameter("favourites1");
    String favourites2 = request.getParameter("favourites2");
    String favourites3 = request.getParameter("favourites3");
    String fav = "";
    if(favourites1!= null){
        fav += favourites1 + " ";
    }
    if(favourites2!= null){
        fav += favourites2 + " ";
    }
    if(favourites3!= null){
        fav += favourites3;
    }
%>
<table>
    <tr>
        <td>Ten</td>
        <td><%=name%>
        </td>
    </tr>
    <tr>
        <td>Mật khẩu</td>
        <td><%=password%>
        </td>
    </tr>
    <tr>
        <td>Điện thoại</td>
        <td><%=phone%>
        </td>
    </tr>
    <tr>
        <td>Giới thiệu</td>
        <td><%=about%>
        </td>
    </tr>
    <tr>
        <td>Sở thích</td>
        <td><%=fav%>
        </td>
    </tr>
</table>
</body>
</html>
