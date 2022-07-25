<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
          integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="stylesheet" href="../../assetss/list.css">
    <link rel="stylesheet" href="../../assetss/create.css">
    <link rel="stylesheet" href="../../assetss/transfer.css">
    <link rel="stylesheet" href="../../assetss/edit.css">
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <title>Update Product</title>
</head>
<body>
<div class="container">
    <div class="row header">
        <div class="col-sm-8 header--left">
            <h1>Update User</h1>
        </div>
        <div class="col-sm-4 header--right list--right">
            <a href="/user_manager">
                <i class="fa-solid fa-list"></i>
                <span>USER LIST</span>
            </a>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12 padding-0">
            <form action="" method="post">
                <c:if test="${requestScope.user != null}">
                    <input type="hidden" name="id" value="<c:out value='${user.getIdUser()}' />"/>
                </c:if>
                <%--                    <c:forEach items="${requestScope['user']}" var="edit_user">--%>

                <div class="row">
                    <div class="col-sm-6">
                        <label for="name">User Name</label>
                        <input type="text" id="name" name="userName" value="<c:out value="${user.getUserName()}"/>">
                        <label for="password">Password</label>
                        <input type="text" id="password" name="password" value="<c:out value="${user.getPassword()}"/>">
                        <label for="phone">Phone</label>
                        <input type="text" id="phone" name="phone" value="<c:out value="${user.getPhone()}"/>">
                    </div>
                    <div class="col-sm-6">
                        <label for="email">Email</label>
                        <input type="text" id="email" name="email" value="<c:out value="${user.getEmail()}"/>">
                        <label >Role</label>
                        <br>
                        <select name="idrole">
                            <c:forEach items="${listRole}" var="role">
                                <option value="${role.getId()}">${role.getName()}</option>
                            </c:forEach>
                        </select>
<%--                        <input type="text" id="idrole" name="idrole" value="<c:out value="${user.getIdrole()}"/>">--%>
                    </div>
                </div>
                <button type="submit">
                    <i class="fa-solid fa-pen-to-square"></i>
                  UPDATE
                </button>
            </form>
        </div>
        <div class="footer" style="margin-left: 200px">
            <c:if test="${requestScope['success'] == true}">
                <ul class="success">
                    <li>Cập nhật thành công</li>
                </ul>
            </c:if>
            <c:if test="${!requestScope['errors'].isEmpty()}">
                <ul class="error">
                    <c:forEach items="${requestScope['errors']}" var="item">
                        <li>${item}</li>
                    </c:forEach>
                </ul>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>