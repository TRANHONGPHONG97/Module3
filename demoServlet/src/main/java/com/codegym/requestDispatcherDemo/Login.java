package com.codegym.requestDispatcherDemo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {
    private HttpServletResponse resp;

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        String user = req.getParameter("user");
        if (user.equals("C0322G1")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/welcome");
            dispatcher.forward(req, resp);
        } else {
            printWriter.println("User khong chinh xac!");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/form-login");
            dispatcher.include(req, resp);
        }
    }
}
