<!DOCTYPE html>
<html lang="en">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Danh sách nhân viên | Quản trị Admin</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="../../assets/css/main.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
    <!-- or -->
    <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">

    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css"
          href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">

</head>

<body onload="time()" class="app sidebar-mini rtl">
<!-- Navbar-->
<header class="app-header">
    <!-- Sidebar toggle button--><a class="app-sidebar__toggle" href="#" data-toggle="sidebar"
                                    aria-label="Hide Sidebar"></a>
    <!-- Navbar Right Menu-->
    <ul class="app-nav">


        <!-- User Menu-->
        <li><a class="app-nav__item" href="/index.html"><i class='bx bx-log-out bx-rotate-180'></i> </a>

        </li>
    </ul>
</header>
<!-- Sidebar menu-->
<div class="app-sidebar__overlay" data-toggle="sidebar"></div>
<aside class="app-sidebar">
    <div class="app-sidebar__user"><img class="app-sidebar__user-avatar" src="/images/hay.jpg" width="50px"
                                        alt="User Image">
    </div>
    <hr>
    <ul class="app-menu">

        <li><a class="app-menu__item active" href="user_manager?action=create"><i class='app-menu__icon bx bx-id-card'></i>
            <span class="app-menu__label">Quản lý nhân viên</span></a></li>

        <li><a class="app-menu__item" href="../../doc/table-data-product.html"><i
                class='app-menu__icon bx bx-purchase-tag-alt'></i><span
                class="app-menu__label">Quản lý sản phẩm</span></a>
        </li>
        <li><a class="app-menu__item" href="../../doc/table-data-oder.html"><i class='app-menu__icon bx bx-task'></i><span
                class="app-menu__label">Quản lý đơn hàng</span></a></li>

    </ul>
</aside>
<main class="app-content">
    <div class="app-title">
        <ul class="app-breadcrumb breadcrumb side">
            <li class="breadcrumb-item active"><a href="#"><b>Danh sách nhân viên</b></a></li>
        </ul>
        <div id="clock"></div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <div class="tile">
                <div class="tile-body">

                    <div class="row element-button">
                        <div class="col-sm-2">

                            <a class="btn btn-add btn-sm" href="user_manager?action=create" title="Thêm"><i
                                    class="fas fa-plus"></i>
                                Tạo mới nhân viên</a>
                        </div>
                        <div class="col-sm-2">
                            <a class="btn btn-delete btn-sm" type="button" title="Xóa" onclick="myFunction(this)"><i
                                    class="fas fa-trash-alt"></i> Xóa tất cả </a>
                        </div>
                        <div>
                            <form action="user_manager">
                                Search: <input type="text" hint="search" value="${requestScope.q}" name="q"> Filter:
                                <select name="idrole" id="">
                                    <%--            <option value="1">Việt Nam</option>--%>
                                    <%--            <option value="2">Pháp</option>--%>
                                    <%--            <option value="3">USA</option>--%>
                                    <%--            <option value="4">China</option>--%>
                                    <option value="-1">All</option>
                                    --%>
                                    <c:forEach items="${applicationScope.listRole}" var="role">

                                        <c:choose>
                                            <c:when test="${role.getId() == requestScope.idrole}">
                                                <option selected value="${role.getId()}">${role.getName()}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${role.getId()}">${role.getName()}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>

                                </select>
                                <button type="get">Submit:</button>
                            </form>
                        </div>
                    </div>
                    <table class="table table-hover table-bordered js-copytextarea" cellpadding="0" cellspacing="0"
                           border="0"
                           id="sampleTable">
                        <thead>
                        <tr>
<%--                            <th width="10"><input type="checkbox" id="all"></th>--%>
                            <th>ID User</th>
                            <th>User Name</th>
                            <th>Password</th>
                            <th>Phone</th>
                            <th>Email</th>
                            <th>Role</th>
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
                                        <c:out value="admin"/>
                                    </c:if>
                                    <c:if test="${user.getIdrole() == 2}">
                                        <c:out value="user"/>
                                    </c:if>
                                </td>
                                <td>
                                    <a href="/user_manager?action=edit&id=${user.getIdUser()}"
                                       class="fas fa-edit">Edit </a>
                                    <a onclick="showMessage(${user.getIdUser()})"
                                       class="fas fa-trash-alt">Delete</a>
                                </td>


                                    <%--                            <td class="table-td-center">--%>
                                    <%--                                <button class="btn btn-primary btn-sm trash" type="button" title="Xóa"--%>
                                    <%--                                        onclick="myFunction(this)"><i class="fas fa-trash-alt"></i>--%>
                                    <%--                                </button>--%>
                                    <%--                                <button class="btn btn-primary btn-sm edit" type="button" title="Sửa" id="show-emp"--%>
                                    <%--                                        data-toggle="modal" data-target="#ModalUP"><i class="fas fa-edit"></i>--%>
                                    <%--                                </button>--%>
                                    <%--                            </td>--%>
                            </tr>
                        </c:forEach>
                        </tbody>

                    </table>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <c:if test="${requestScope.currentPage != 1}">
                                <li class="page-item ">
                                    <a class="page-link " href="user_manager?page=${requestScope.currentPage - 1}">Previous</a>
                                </li>
                            </c:if>

                            <%--Để hiển thị số Trang.
                            Điều kiện khi không hiển thị liên kết cho trang hiện tại--%>

                            <c:forEach begin="1" end="${noOfPages}" var="i">
                                <c:choose>
                                    <c:when test="${requestScope.currentPage eq i}">
                                        <li class="page-item "><a class="page-link" href="user_manager?page=${i}">${i}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item ">
                                            <a class="page-link"
                                               href="user_manager?page=${i}">${i}</a> </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                            <%--Để hiển thị liên kết Tiếp theo--%>

                            <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                                <li class="page-item ">
                                    <a class="page-link" href="user_manager?page=${requestScope.currentPage + 1}">Next</a>
                                </li>
                            </c:if>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</main>

<!--
MODAL
-->
<div class="modal fade" id="ModalUP" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static"
     data-keyboard="false">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">

            <div class="modal-body">
                <div class="row">
                    <div class="form-group  col-md-12">
              <span class="thong-tin-thanh-toan">
                <h5>Chỉnh sửa thông tin nhân viên cơ bản</h5>
              </span>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-6">
                        <label class="control-label">ID nhân viên</label>
                        <input class="form-control" type="text" required value="#CD2187" disabled>
                    </div>
                    <div class="form-group col-md-6">
                        <label class="control-label">Họ và tên</label>
                        <input class="form-control" type="text" required value="Võ Trường">
                    </div>
                    <div class="form-group  col-md-6">
                        <label class="control-label">Số điện thoại</label>
                        <input class="form-control" type="number" required value="09267312388">
                    </div>
                    <div class="form-group col-md-6">
                        <label class="control-label">Địa chỉ email</label>
                        <input class="form-control" type="text" required value="truong.vd2000@gmail.com">
                    </div>
                    <div class="form-group col-md-6">
                        <label class="control-label">Ngày sinh</label>
                        <input class="form-control" type="date" value="15/03/2000">
                    </div>
                    <div class="form-group  col-md-6">
                        <label for="exampleSelect1" class="control-label">Chức vụ</label>
                        <select class="form-control" id="exampleSelect1">
                            <option>Bán hàng</option>
                            <option>Tư vấn</option>
                            <option>Dịch vụ</option>
                            <option>Thu Ngân</option>
                            <option>Quản kho</option>
                            <option>Bảo trì</option>
                            <option>Kiểm hàng</option>
                            <option>Bảo vệ</option>
                            <option>Tạp vụ</option>
                        </select>
                    </div>
                </div>
                <BR>
                <a href="#" style="    float: right;
        font-weight: 600;
        color: #ea0000;">Chỉnh sửa nâng cao</a>
                <BR>
                <BR>
                <button class="btn btn-save" type="button">Lưu lại</button>
                <a class="btn btn-cancel" data-dismiss="modal" href="#">Hủy bỏ</a>
                <BR>
            </div>
            <div class="modal-footer">
            </div>
        </div>
    </div>
</div>
<!--
MODAL
-->

<!-- Essential javascripts for application to work-->
<script src="../../assets/js/jquery-3.2.1.min.js"></script>
<script src="../../assets/js/popper.min.js"></script>
<script src="../../assets/js/bootstrap.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="src/jquery.table2excel.js"></script>
<script src="../../assets/js/main.js"></script>
<!-- The javascript plugin to display page loading on top-->
<script src="../../assets/js/plugins/pace.min.js"></script>
<!-- Page specific javascripts-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
<!-- Data table plugin-->
<script type="text/javascript" src="../../assets/js/plugins/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="../../assets/js/plugins/dataTables.bootstrap.min.js"></script>
<script>
    function showMessage(id) {
        // var delete = window.confirm("Ban muon xoa khong?")
        // if(delete ===true ){
        //     window.location.href ="/user_manager?action=delete?id=" +id;
        // }

        if (confirm("ban xoa") === true){
            window.location.href ="/user_manager?action=delete&id=" +id;
        }
    }
</script>
</body>

</html>