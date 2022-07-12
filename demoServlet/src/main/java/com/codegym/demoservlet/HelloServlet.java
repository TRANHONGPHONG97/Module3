package com.codegym.demoservlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {


    @Override
    public void init() throws ServletException {
        System.out.println("Bat dau servlet");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        System.out.println("Phuong thuc cua request " + req.getMethod());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<html><body>");
        printWriter.println("<h1> Xin chao C0322G1 </h1>");
        printWriter.println("</body></html>");
        printWriter.close();
    }

    @Override
    public void destroy() {
        System.out.println("Ket thuc servlet");
    }
}