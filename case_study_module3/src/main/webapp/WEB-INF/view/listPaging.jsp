<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../assets/list.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />


    <title>User Manager</title>
</head>
<body>

<div class="container">
<%--    Search: <input type="text" hint="search" value="${requestScope.q}" name="q"> Filter:--%>
<%--    <select name="idrole" id="">--%>
    <div class="row header">
        <div class="col-sm-6 header--left">
            <h1>List of Users</h1>
        </div>
        <div class="col-sm-6 header--right">
            <a href="#">
                <i class="fa-solid fa-clock-rotate-left"></i>
                <span>Transfer money Information</span>
            </a>
            <a href="/user_manager?action=create">
                <i class="fa-solid fa-square-plus"></i>
                <span>Add New User</span>
            </a>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12 padding-0">
            <table class="table">

                <thead>
                <tr>
                    <th>IdUser</th>
                    <th>User Name</th>
                    <th>Password</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Id Role</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${requestScope.list}">

                <tr>
                    <td><c:out value="${user.getIdUser()}"/></td>
                    <td><c:out value="${user.getUserName()}"/></td>
                    <td><c:out value="${user.getPassword()}"/></td>
                    <td><c:out value="${user.getPhone()}"/></td>
                    <td><c:out value="${user.getEmail()}"/></td>

                    <td>
                        <c:if test="${user.getIdrole() == 1}">
                            <c:out value="Admin"/>
                        </c:if>
                        <c:if test="${user.getIdrole() == 2}">
                            <c:out value="User"/>
                        </c:if>

                    </td>
                    <td>
                        <a href="/user_manager?action=edit&id=${user.getIdUser()}" class="black">
                            <i class="fa-solid fa-pen-to-square"></i>
                        </a>
                            <%--                                <a href="#" class="green">--%>
                            <%--                                    <i class="fa-solid fa-plus"></i>--%>
                            <%--                                </a>--%>
                            <%--                                <a href="#" class="yellow">--%>
                            <%--                                    <i class="fa-solid fa-minus"></i>--%>
                            <%--                                </a>--%>
                            <%--                                <a href="#" class="blue">--%>
                            <%--                                    <i class="fa-solid fa-arrow-right-arrow-left"></i>--%>
                            <%--                                </a>--%>
                        <a href="/user_manager?action=delete&id=${user.getIdUser()}" class="red">
                            <i class="fa-solid fa-ban"></i>
                        </a>
                    </td>
                </tr>

                </c:forEach>
            </tbody>
            </table>
            </table>

        </div>
    </div>

</div>

</body>
</html>
