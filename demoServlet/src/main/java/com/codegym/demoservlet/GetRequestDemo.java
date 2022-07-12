package com.codegym.demoservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet (name = "getRequestDemo", urlPatterns = "/getrequestdemo")
public class GetRequestDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("test");
        String name = req.getParameter("ten");

        Integer age = Integer.valueOf(req.getParameter("tuoi"));
        printWriter.println("Xin chao " + name  + " " + age + " tuoi ");
//        => test
//           Xin chao null
//  cách làm: http://localhost:8080/getrequestdemo?ten=Phong
//   cách làm: http://localhost:8080/getrequestdemo?ten=Phong&tuoi=25
    }
}
