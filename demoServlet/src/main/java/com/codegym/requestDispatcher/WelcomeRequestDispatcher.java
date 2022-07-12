package com.codegym.requestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(urlPatterns = "/Welcome1")
public class WelcomeRequestDispatcher extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        processRequest(req,resp);
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        String username = req.getParameter("user");
        printWriter.println("Hello " + username);
    }

}
