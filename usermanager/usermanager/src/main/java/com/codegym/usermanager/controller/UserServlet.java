package com.codegym.usermanager.controller;

import com.codegym.usermanager.dao.CountryDAO;
import com.codegym.usermanager.dao.ICountryDAO;
import com.codegym.usermanager.dao.UserDAO;
import com.codegym.usermanager.model.User;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@WebServlet(name = "UserServlet", urlPatterns = "/users")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 50)
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;
    private ICountryDAO iCountryDAO;
    private String errors = "";

    public void init() {
        userDAO = new UserDAO();
        iCountryDAO = new CountryDAO();
        if (this.getServletContext().getAttribute("listCountry") == null) {
            this.getServletContext().setAttribute("listCountry", iCountryDAO.selectAllCountry());
        }
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
                case "createupload":
                    insertUserUpload(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    showNewForm(request, response);
                    break;
                case "createupload":
                    showNewFormUpload(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteUser(request, response);
                    break;
                default:
//                    listUser(request, response);
                    listUserPagging(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listUserPagging(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 3;
        String q = "";
        int idcountry = -1;
        if (request.getParameter("q") != null) {
            q = request.getParameter("q");
        }
        if (request.getParameter("idcountry") != null) {
            idcountry = Integer.parseInt(request.getParameter("idcountry"));
        }
        if (request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        List<User> listUser = userDAO.selectUsersPaging((page - 1) * recordsPerPage, recordsPerPage, q, idcountry);
        int noOfRecords = userDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("listUser", listUser);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("q", q);
        request.setAttribute("idcountry", idcountry);
        RequestDispatcher view = request.getRequestDispatcher("user/list_paging.jsp");
        view.forward(request, response);
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> listUser = userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        List<Country> listCountry = iCountryDAO.getAllCountry();
//        request.setAttribute("listCountry", listCountry);
        User user = new User();
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/create.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewFormUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        lấy đường dẫn úp file lên server ở trong file của project
        System.out.println("servlet path " + this.getServletContext().getRealPath("/"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/create_upload.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDAO.selectUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/edit.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        User user = new User();
        boolean flag = true;
        Map<String, String> hashMap = new HashMap<String, String>();
        System.out.println(this.getClass() + " insertUser with validate");
        try {
            user.setId(Integer.parseInt(request.getParameter("id")));
            String email = request.getParameter("email");
            user.setEmail(email);
            user.setName(request.getParameter("name"));
            user.setPassword(request.getParameter("password"));
            System.out.println(this.getClass() + "Country value from request: " + request.getParameter("country"));
            int idCountry = Integer.parseInt(request.getParameter("country"));
            user.setIdcountry(idCountry);
            System.out.println(this.getClass() + "User info from request: " + user);
            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
            System.out.println("User: " + user);
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
//                List<Country> listCountry = iCountryDAO.selectAllCountry();
//                request.setAttribute("listCountry", listCountry);
                System.out.println(this.getClass() + " !constraintViolations.isEmpty()");
                request.getRequestDispatcher("/user/create.jsp").forward(request, response);
            } else {
                if (userDAO.selectUserByEmail(email) != null) {
                    flag = false;
                    hashMap.put("email", "Email exits in database");
                    System.out.println(this.getClass() + " Email exits in database");
                }
                if (iCountryDAO.selectCountry(idCountry) == null) {
                    flag = false;
                    hashMap.put("country", "Country value invalid");
                    System.out.println(this.getClass() + " Country invalid");
                }
                if (flag) {
                    userDAO.insertUser(user);
                    User u = new User();
                    request.setAttribute("user", u);
                    request.getRequestDispatcher("user/create.jsp").forward(request, response);
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
                    System.out.println(this.getClass() + " !constraintViolations.isEmpty()");
                    request.getRequestDispatcher("/user/create.jsp").forward(request, response);
                }
            }
        } catch (NumberFormatException ex) {
            System.out.println(this.getClass() + " NumberFormatException: User info from request: " + user);
            errors = "";
            errors += "" + "Input format not right"
                    + "";
            errors += "";
            request.setAttribute("user", user);
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/user/create.jsp").forward(request, response);
        } catch (Exception ex) {
        }
    }

    private void insertUserUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int idcountry = Integer.parseInt(request.getParameter("idcountry"));
        String password = request.getParameter("password");
        User newUser = new User(name, email, idcountry, password);

//        User user = new User();
//        boolean flag = true;
//        Map<String, String> hashMap = new HashMap<String, String>();
//        System.out.println(this.getClass() + " insertUser with validate");
//        try {
//            user.setId(Integer.parseInt(request.getParameter("id")));
//            String email = request.getParameter("email");
//            user.setEmail(email);
//            user.setName(request.getParameter("name"));
//            user.setPassword(request.getParameter("password"));
//            System.out.println(this.getClass() + "Country value from request: " + request.getParameter("country") );
//            int idCountry = Integer.parseInt(request.getParameter("country"));
//            user.setIdcountry(idCountry);
//            System.out.println(this.getClass() + "User info from request: " + user);
//            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
//            Validator validator = validatorFactory.getValidator();
//            Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
//            System.out.println("User: " + user);
//            if (!constraintViolations.isEmpty()) {
//
//                errors  = "";
//                // constraintViolations is has error
//                for (ConstraintViolation<User> constraintViolation : constraintViolations) {
//                    errors += "" + constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage()
//                            + "";
//                }
//                errors += "";
//                request.setAttribute("user", user);
//                request.setAttribute("errors", errors);
////                List<Country> listCountry = iCountryDAO.selectAllCountry();
////                request.setAttribute("listCountry", listCountry);
//                System.out.println(this.getClass() + " !constraintViolations.isEmpty()");
//                request.getRequestDispatcher("/user/create.jsp").forward(request, response);
//            }else{
//                if(userDAO.selectUserByEmail(email)!=null) {
//                    flag = false;
//                    hashMap.put("email", "Email exits in database");
//                    System.out.println(this.getClass() + " Email exits in database");
//                }
//                if(iCountryDAO.selectCountry(idCountry)==null) {
//                    flag = false;
//                    hashMap.put("country", "Country value invalid");
//                    System.out.println(this.getClass() + " Country invalid");
//                }
//                if(flag) {
//                    userDAO.insertUser(user);
//                    User u = new User();
//                    request.setAttribute("user", u);
//                    request.getRequestDispatcher("user/create.jsp").forward(request, response);
//                }else {
//                    errors = "";
//                    hashMap.forEach(new BiConsumer<String, String>() {
//                        @Override
//                        public void accept(String keyError, String valueError) {
//                            errors += ""  + valueError
//                                    + "";
//                        }
//                    });
//                    errors +="";
//                    request.setAttribute("user", user);
//                    request.setAttribute("errors", errors);
//                    System.out.println(this.getClass() + " !constraintViolations.isEmpty()");
//                    request.getRequestDispatcher("/user/create.jsp").forward(request, response);
//                }
//            }
//        }
//        catch (NumberFormatException ex) {
//            System.out.println(this.getClass() + " NumberFormatException: User info from request: " + user);
//            errors = "";
//            errors += "" + "Input format not right"
//                    + "";
//            errors += "";
//            request.setAttribute("user", user);
//            request.setAttribute("errors", errors);
//            request.getRequestDispatcher("/user/create.jsp").forward(request, response);
//        }
//        catch(Exception ex){
//        }
        for (Part part : request.getParts()) {
            System.out.println("ContentType of part " + part.getContentType());
            System.out.println("Name of part " + part.getName());
            if (part.getName().equals("file")) {
                String fileName = extractFileName(part);
                fileName = new File(fileName).getName();
                part.write("D:\\Module3\\usermanager\\usermanager\\src\\main\\webapp\\images\\" + fileName);
                String servletRealPath = this.getServletContext().getRealPath("/") + "\\images\\" + fileName;
                System.out.println("serletRealPath: " + servletRealPath);
                part.write(servletRealPath);
                newUser.setUrlImage("images\\" + fileName);
            }
        }
        userDAO.insertUser(newUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/create_upload.jsp");
        dispatcher.forward(request, response);
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    public File getFolderUpload() {
        File folderUpload = new File(System.getProperty("user.home") + "/Uploads");
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int idcountry = Integer.parseInt(request.getParameter("idcountry"));
        User user = new User(id, name, email, idcountry);
        userDAO.updateUser(user);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("user/edit.jsp");
//          dispatcher.forward(request, response);
        response.sendRedirect("/users");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        userDAO.deleteUser(id);
//        List<User> listUser = userDAO.selectAllUsers();
//        request.setAttribute("listUser", listUser);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
//        dispatcher.forward(request, response);

        int id = Integer.parseInt(request.getParameter("id"));
        User customer = this.userDAO.selectUser(id);
        RequestDispatcher dispatcher;
        if (customer == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            this.userDAO.deleteUser(id);
            try {
                response.sendRedirect("/users");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
       User user = this.userDAO.selectUser(id);
        RequestDispatcher dispatcher;
        if (user == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("user", user);
            dispatcher = request.getRequestDispatcher("user/delete.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
