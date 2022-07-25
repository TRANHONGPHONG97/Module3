<!DOCTYPE html>
<html lang="en">
<head>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="../../assetss/list.css">
    <link rel="stylesheet" href="../../assetss/create.css">
    <link rel="stylesheet" href="../../assetss/transfer.css">
    <title>Create Product</title>
</head>
<body>
<div class="container">
    <div class="row header">
        <div class="col-sm-6 header--left">
            <h1>Create New Product</h1>
        </div>
        <div class="col-sm-6 header--right list--right">
            <a href="product">
                <i class="fa-solid fa-list"></i>
                <span>PRODUCT LIST</span>
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
                        <label >PRODUCT NAME</label>
                        <input type="text" name="name" id="name" size="45" value ="${product.getName() }"/>
                        <label >IMAGE</label>
                        <input type="text" name="image" value="${product.getImage()}" size="45"/>
                        <label>PRICE</label>
                        <input type="number" name="price" id="phone" size="45" value ="${product.getPrice() }"/>
                    </div>
                    <div class="col-sm-6">
                        <label>QUANTITY</label>
                        <input type="number" name="quantity" id="email" size="45" value ="${product.getQuantity() }"/>
                        <label>CATEGORY</label>
                        <div class="col-md-1">
                            <select name="category_id">
                                <c:forEach items="${listCategory}" var="category">
                                    <option value="${category.getId()}">${category.getName()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <button type="submit">
                    <i class="fa-solid fa-plus"></i>
                    Create
                </button>
            </form>
        </div>
        <div class="footer" style="margin-left: 200px">
            <c:if test="${requestScope['success'] == true}">
                <ul class="success">
                    <li>Thêm mới thành công</li>
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
<%--<div class="page-error" id="errors">--%>
<%--    ${errors}--%>
<%--</div>--%>
</html>