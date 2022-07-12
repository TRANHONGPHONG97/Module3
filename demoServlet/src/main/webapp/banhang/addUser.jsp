<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/10/2022
  Time: 8:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Thêm người dùng mới</h1>
<form action = "/banhang/viewUser.jsp" method = "post">
    <input type="text" name = "name" placeholder="Tên người dùng"> <br>
    <input type="password" name = "password" placeholder="Mật khẩu"> <br>
    <input type="text" name = "phone" placeholder="Điện thoại"> <br>
    <textarea name="about" cols="30" rows="3" placeholder="Giới thiệu"></textarea> <br>
    <input type="checkbox" name = "favourites1" value="Xem phim"> Xem phim
    <input type="checkbox" name = "favourites2" value="Đá bóng"> Đá bóng
    <input type="checkbox" name = "favourites3" value="Bi-a"> Bi-a

    <input type="submit" value="Add">
</form>
</body>
</html>
