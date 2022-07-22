
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Country manager</title>
</head>
<body>
<center>
    <h1>Country Edit</h1>
    <h2>
        <!-- <a href="users?action=users">List All Users</a> -->
        <a href="country">List All Country</a>
    </h2>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>Edit Country</h2>
            </caption>
            <tr>
                <th>Country Name:</th>
                <input type="hidden" name="id" value="${country.getId() }" />
                <td>
                    <input type="text" name="name" id="name" size="45" value = "${country.getName() }"/>
                </td>
            </tr>
<%--            <tr>--%>
<%--                <th>User password:</th>--%>
<%--                <td>--%>
<%--                    &lt;%&ndash;                    <input type="password" name="password" id="password" size="45"  value=&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                    "<c:forEach items="${listCountry}" var="password">&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                         ${country.getId()}${country.getPassword()}&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                    </c:forEach>"&ndash;%&gt;--%>
<%--                    <input type="password" name="password" id="password" size="45"  value="<c:out value ='${user.getPassword()}'/>"/>--%>
<%--                </td>--%>
<%--            </tr>--%>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>