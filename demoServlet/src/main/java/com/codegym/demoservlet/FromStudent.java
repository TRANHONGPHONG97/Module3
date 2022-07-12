package com.codegym.demoservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet (name = "FromStudent", urlPatterns = "/FromStudent")
public class FromStudent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
//        printWriter.println("<form action ='/PostStudentServlet' method = 'post'>");
        printWriter.println("<form action ='/PostStudentServlet' method = 'get'>");
        printWriter.println("Ten: <input type = 'text' name = 'Ten'>");
        printWriter.println("Tuoi: <input type = 'text' name = 'Tuoi'>");
        printWriter.println("Dia_chi: <input type = 'text' name = 'Dia_chi'>");
        printWriter.println("Lop: <input type = 'text' name = 'Lop'>");
        printWriter.println("<input type = 'submit' value = 'submit'>");
        printWriter.println("</form>");
    }
}
