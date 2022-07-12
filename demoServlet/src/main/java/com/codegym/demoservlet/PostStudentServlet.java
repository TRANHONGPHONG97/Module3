package com.codegym.demoservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet (name="PostStudentServlet", urlPatterns = "/PostStudentServlet")
public class PostStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        String Ten = req.getParameter("Ten");
        String Tuoi = req.getParameter("Tuoi");
        String Dia_chi = req.getParameter("Dia_chi");
        String Lop = req.getParameter("Lop");

        printWriter.println("Tên: " + Ten + ", " + "Tuoi: " + Tuoi + ", " + "Dia_chi: " + Dia_chi + ", " + "Lop: " + Lop);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("Phuong thuc Get: ");
        String Ten = req.getParameter("Ten");
        String Tuoi = req.getParameter("Tuoi");
        String Dia_chi = req.getParameter("Dia_chi");
        String Lop = req.getParameter("Lop");

        printWriter.println("Tên: " + Ten + ", " + "Tuoi: " + Tuoi + ", " + "Dia_chi: " + Dia_chi + ", " + "Lop: " + Lop);
    }
}
