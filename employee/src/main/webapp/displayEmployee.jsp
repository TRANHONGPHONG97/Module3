<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/17/2022
  Time: 3:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <th>employee_Id</th>
        <th>employeeName</th>
        <th>salary</th>
        <th>deptName</th>
    </tr>

    <c:forEach var="employee" items="${employeeList}">
        <tr>
            <td>${employee.employee_Id}</td>
            <td>${employee.employeeName}</td>
            <td>${employee.salary}</td>
            <td>${employee.deptName}</td>
        </tr>
    </c:forEach>
</table>

<%--For displaying Previous link except for the 1st page --%>
<c:if test="${currentPage != 1}">
    <td><a href="employee.do?page=${currentPage - 1}">Previous</a></td>
</c:if>

<%--For displaying Page numbers.
The when condition does not display a link for the current page--%>
<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="employee.do?page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>

<%--For displaying Next link --%>
<c:if test="${currentPage lt noOfPages}">
    <td><a href="employee.do?page=${currentPage + 1}">Next</a></td>
</c:if>
</body>
</html>
