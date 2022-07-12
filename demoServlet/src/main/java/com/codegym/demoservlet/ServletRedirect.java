package com.codegym.demoservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (name = "ServletRedirect", urlPatterns = "/ServletRedirect")
public class ServletRedirect extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("https://www.facebook.com/huynhthi.phuongngan");
//        resp.setStatus(resp.SC_MOVED_PERMANENTLY);
//        resp.setHeader("Location", "https://www.facebook.com/huynhthi.phuongngan");
    }
}
