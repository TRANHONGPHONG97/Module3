package com.codegym.demoservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseServletDemo", urlPatterns = "/responseservletdemo")
public class ResponseServletDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//       resp.setContentType("text/html");
        resp.setContentType("text/plain");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<h1> Heloo C0322G1 </h1>");
//        System.out.println("<h1> Heloo C0322G1 </h1>");
//        resp.addCookie(new Cookie("a", "a"));
//        resp.addHeader("a", "aa");
//        resp.sendError(200);
    }
}
