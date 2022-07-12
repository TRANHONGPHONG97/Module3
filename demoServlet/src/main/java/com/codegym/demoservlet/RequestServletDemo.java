package com.codegym.demoservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet (name="requestServletDemo", urlPatterns = "/requestservletdemo")
public class RequestServletDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Phuong thuc cua request " + req.getMethod());
        req.getContentType();
        req.getContextPath();
        System.out.println(req.getServerName());
        System.out.println(req.getServletPath());
        Enumeration <String> keys = req.getHeaderNames();
        while (keys.hasMoreElements()){
            String key = keys.nextElement();
            System.out.println(key + " : " + req.getHeader(key));
        }
    }
}
