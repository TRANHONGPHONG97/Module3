<%@ page import="com.codegym.usermanager.model.User" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/12/2022
  Time: 9:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management Application</title>
</head>
<body>
<center>
    <h1>User Management</h1>
    <h2>
        <a href="/users?action=create">Add New User</a>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Users</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Country</th>
            <th>Actions</th>
        </tr>
        <%
            if(request.getAttribute("listUser")==null){
                System.out.println("List user is null");
            }
        %>
        <c:forEach var="user" items="${listUser}">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.country}"/></td>
                <td>
                    <a href="/users?action=edit&id=${user.id}">Edit</a>
                    <a href="/users?action=delete&id=${user.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
<%--        <%--%>
<%--            String strOut = "";--%>
<%--            for (User u : (List<User>) request.getAttribute("listUser")) {--%>
<%--                strOut += "<tr>";--%>
<%--                strOut += "<td>" + u.getId() +"</td>";--%>
<%--                strOut += "<td>" + u.getName() +"</td>";--%>
<%--                strOut += "<td>" + u.getEmail() +"</td>";--%>
<%--                strOut += "<td>" + u.getCountry() +"</td>";--%>
<%--                strOut +="</tr>";--%>
<%--            }--%>

<%--            out.println(strOut);--%>
<%--        %>--%>
    </table>
</div>
</body>
</html>
