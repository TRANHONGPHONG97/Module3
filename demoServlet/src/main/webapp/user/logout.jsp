<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/10/2022
  Time: 8:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
//    response.sendRedirect("/Hello.jsp");
    response.setStatus(301);
    response.addHeader("Location","/Hello.jsp");
%>
