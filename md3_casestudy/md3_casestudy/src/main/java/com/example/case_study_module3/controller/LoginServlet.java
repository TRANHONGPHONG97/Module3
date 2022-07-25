package com.example.case_study_module3.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            HttpSession session = request.getSession();

            if (username.equals("su") && password.equals("123")) {
                session.setAttribute("quyen", "su");
                response.sendRedirect("wellcome.jsp");
            } else if (username.equals("ad") && password.equals("123")) {
                session.setAttribute("quyen", "ad");
                response.sendRedirect("wellcome.jsp");
            } else if (username.equals("em") && password.equals("123")) {
                session.setAttribute("quyen", "em");
                response.sendRedirect("wellcome.jsp");
            } else {
                request.setAttribute("message", "Tai khoan k hop le!!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
    }
}
