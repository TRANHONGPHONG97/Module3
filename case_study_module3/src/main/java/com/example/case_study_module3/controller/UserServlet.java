package com.example.case_study_module3.controller;

import com.example.case_study_module3.dao.IRoleDao;
import com.example.case_study_module3.dao.RoleDao;
import com.example.case_study_module3.dao.UserDao;
import com.example.case_study_module3.model.Role;
import com.example.case_study_module3.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.function.BiConsumer;

@WebServlet(urlPatterns = "/user_manager")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 50)
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private String errors = "";
    UserDao userDao;
    private IRoleDao iRoleDao;
    RequestDispatcher dispatcher;

    @Override
    public void init() throws ServletException {
        userDao = new UserDao();
        iRoleDao = new RoleDao();
        if (this.getServletContext().getAttribute("listRole") == null) {
            this.getServletContext().setAttribute("listRole", iRoleDao.selectAllRole());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    showCreateForm(req, resp);
                    break;
                case "edit":
                    showEditForm(req, resp);
                    break;
                case "delete":
                    showDeleteForm(req, resp);
                    break;
                default:
//                    listUser(req, resp);
                listUserPaging(req, resp);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listUserPaging(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 3;
        String q = "";
        int idrole = -1;
        if (request.getParameter("q") != null) {
            q = request.getParameter("q");
        }
        if (request.getParameter("idrole") != null) {
            idrole = Integer.parseInt(request.getParameter("idrole"));
        }
        System.out.println(idrole + " tao la role");
        if (request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        List<User> listUser = userDao.selectUsersPaging((page - 1) * recordsPerPage, recordsPerPage,q, idrole);
        int noOfRecords = userDao.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("list", listUser);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("q", q);
        request.setAttribute("idrole", idrole);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/listUser.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    insertUser(request, response);
                    break;
                case "edit":
                    updateUser(request, response);
                    break;
                default:
                    listUser(request, response);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDao.deleteUser(id);
        List<User> list = userDao.selectAllUsers();
        request.setAttribute("list", list);


        response.sendRedirect("/user_manager");
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/user_manager");
//        dispatcher.forward(request, response);
//        int page = 1;
//        int recordsPerPage = 3;
//        String q = "";
//        int idrole = -1;
//        if (request.getParameter("q") != null) {
//            q = request.getParameter("q");
//        }
//        if (request.getParameter("idrole") != null) {
//            idrole = Integer.parseInt(request.getParameter("idrole"));
//        }
//        System.out.println(idrole + " tao la role");
//        if (request.getParameter("page") != null)
//            page = Integer.parseInt(request.getParameter("page"));
//        List<User> listUser = userDao.selectUsersPaging((page - 1) * recordsPerPage, recordsPerPage,q, idrole);
//        int noOfRecords = userDao.getNoOfRecords();
//        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
//
//        request.setAttribute("list", listUser);
//        request.setAttribute("noOfPages", noOfPages);
//        request.setAttribute("currentPage", page);
//        request.setAttribute("q", q);
//        request.setAttribute("idrole", idrole);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/listUser.jsp");
//        dispatcher.forward(request, response);
    }



    private void showCreateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = new User();
        req.setAttribute("user", user);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/create.jsp");
        dispatcher.forward(req, resp);

    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User existingUser = userDao.selectUser(id);
        req.setAttribute("user", existingUser);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/edit.jsp");
        dispatcher.forward(req, resp);
    }

    private void listUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> list = userDao.selectAllUsers();
        req.setAttribute("list", list);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/listUser.jsp");
        dispatcher.forward(req, resp);

    }


    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("userName");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        int idRole = Integer.parseInt(request.getParameter("idrole"));
        User user = new User(id, name, password, phone, email, idRole);
        userDao.updateUser(user);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/edit.jsp");
//          dispatcher.forward(request, response);
        response.sendRedirect("/user_manager");
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

//        System.out.println("tao là post");
//        String userName = request.getParameter("userName");
//        String password = request.getParameter("password");
//        String phone = request.getParameter("phone");
//        String email = request.getParameter("email");
//        int idrole = Integer.parseInt(request.getParameter("idrole"));
//
//        User user = new User(userName, password, phone, email, idrole);
//        userDao.insertUser(user);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/create.jsp");
//        dispatcher.forward(request, response);
////        response.sendRedirect("/user_manager");

            User user = new User();
            boolean flag = true;
            Map<String, String> hashMap = new HashMap<String, String>();

            try {
                user.setIdUser(Integer.parseInt(request.getParameter("idUser")));
                String phone = request.getParameter("phone");
                user.setPhone(phone);
                String email = request.getParameter("email");
                user.setEmail(email);
                String userName = request.getParameter("userName");
                user.setUserName(userName);
                user.setPassword(request.getParameter("password"));
                int idRole = Integer.parseInt(request.getParameter("idrole"));
                ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
                Validator validator = validatorFactory.getValidator();
                Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

                if (!constraintViolations.isEmpty()) {

                    errors = "";
                    // constraintViolations is has error
                    for (ConstraintViolation<User> constraintViolation : constraintViolations) {
                        errors += "" + constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage()
                                + "";
                    }
                    errors += "";
                    request.setAttribute("user", user);
                    request.setAttribute("errors", errors);
                List<Role> roleList = iRoleDao.selectAllRole();
                request.setAttribute("listRole", roleList);
                    request.getRequestDispatcher("/WEB-INF/view/create.jsp").forward(request, response);
                } else {
                    if (userDao.selectUserByEmail(email) != null) {
                        flag = false;
                        hashMap.put("email", "Email exits in database");
                        System.out.println(this.getClass() + " Email exits in database");
                    }
                    if (userDao.selectUserByUserName(userName) != null) {
                        flag = false;
                        hashMap.put("user", "UserName exits in database");
                        System.out.println(this.getClass() + " User Name exits in database");
                    }
                    if (userDao.selectUserByPhone(phone) != null) {
                        flag = false;
                        hashMap.put("phone", "Phone exits in database");
                        System.out.println(this.getClass() + " User Name exits in database");
                    }
                    if (iRoleDao.selectRole(idRole) == null) {
                        flag = false;
                        hashMap.put("user", "Country value invalid");
                        System.out.println(this.getClass() + " Country invalid");
                    }
                    if (flag) {
                        userDao.insertUser(user);
                        User u = new User();
                        request.setAttribute("user", u);
                        request.getRequestDispatcher("WEB-INF/view/create.jsp").forward(request, response);
                    } else {
                        errors = "";
                        hashMap.forEach(new BiConsumer<String, String>() {
                            @Override
                            public void accept(String keyError, String valueError) {
                                errors += "" + valueError
                                        + "";
                            }
                        });
                        errors += "";

                        request.setAttribute("user", user);
                        request.setAttribute("errors", errors);
                        request.getRequestDispatcher("/WEB-INF/view/create.jsp").forward(request, response);
                    }
                }
            } catch (NumberFormatException ex) {
                errors = "";
                errors += "" + "Input format not right"
                        + "";
                errors += "";
                request.setAttribute("user", user);
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("/WEB-INF/view/create.jsp").forward(request, response);
            } catch (Exception ex) {
            }
        }

    }
