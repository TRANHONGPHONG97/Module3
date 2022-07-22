<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Deleting user</title>
</head>
<body>
<h1>Delete user</h1>
<p>
    <a href="/customers">Back to user list</a>
</p>
<form method="post">
    <h3>Are you sure?</h3>
    <fieldset>
        <legend>User information</legend>
        <table>
            <tr>
                <td>Name: </td>
                <td>${requestScope["user"].getName()}</td>
            </tr>
            <tr>
                <td>Email: </td>
                <td>${requestScope["user"].getEmail()}</td>
            </tr>
            <tr>
                <td>Address: </td>
                <td>${requestScope["user"].getAddress()}</td>
            </tr>
            <tr>
                <td><input type="submit" value="Delete user"></td>
                <td><a href="/user">Back to user list</a></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
