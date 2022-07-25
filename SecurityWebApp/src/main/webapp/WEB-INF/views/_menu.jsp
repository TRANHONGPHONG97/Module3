<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/19/2022
  Time: 8:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<a href="${pageContext.request.contextPath}/employeeTask">
    Employee Task
</a>
||
<a href="${pageContext.request.contextPath}/managerTask">
    Manager Task
</a>
||
<a href="${pageContext.request.contextPath}/userInfo">
    User Info
</a>
||
<a href="${pageContext.request.contextPath}/login">
    Login
</a>
||
<a href="${pageContext.request.contextPath}/logout">
    Logout
</a>

&nbsp;
<span style="color:red">[ ${loginedUser.userName} ]</span>
