<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/12/2022
  Time: 10:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Discount Calculator</title>
</head>
<body>
<div id="content">
    <%
        float price = (float) request.getAttribute("price");
        float percent = (float) request.getAttribute("percent");
        float amount = (float) request.getAttribute("amount");
    %>
    <h1>Product Discount Calculator</h1>
    <span></span><br/>
    <label>Discount Price: <%= price%></label> <br>
    <label>Discount Percent: <%= percent%></label><br>
    <label>Discount Amount: <%= amount%></label>
</div>
</body>
</html>
