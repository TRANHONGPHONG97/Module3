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
  <title>Edit User</title>
</head>
<body>
<div class="container">
  <div class="row header">
    <div class="col-sm-8 header--left">
      <h1>Update User information</h1>
    </div>
    <div class="col-sm-4 header--right list--right">
      <a href="/product">
        <i class="fa-solid fa-list"></i>
        <span>List of User</span>
      </a>
    </div>
  </div>
  <div class="row">
    <div class="col-sm-12 padding-0">
      <form action="" method="post">
        <c:if test="${requestScope.product != null}">
          <input type="hidden" name="id" value="<c:out value='${product.getId()}' />"/>
        </c:if>

        <div class="row">
          <div class="col-sm-6">
            <label for="">PRODUCT Name</label>
            <input type="text" id="name" name="name" value="<c:out value="${product.getName()}"/>">
            <label for="">IMAGE</label>
            <input type="text" id="password" name="image" value="<c:out value="${product.getImage()}"/>">
            <label for="">PRICE</label>
            <input type="text" id="phone" name="price" value="<c:out value="${product.getPrice()}"/>">
          </div>
          <div class="col-sm-6">
            <label for="">QUANTITY</label>
            <input type="text" id="email" name="quantity" value="<c:out value="${product.getQuantity()}"/>">
            <label for="">CATEGORY</label>
            <input type="text" id="category_id" name="category_id" value="<c:out value="${product.getCategory_id()}"/>">
          </div>
        </div>
        <button type="submit">
          <i class="fa-solid fa-pen-to-square"></i>
          Save changes
        </button>

      </form>
    </div>
  </div>
</div>
</body>
</html>