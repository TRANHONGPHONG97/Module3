package com.codegym.requestDispatcher;

import com.sun.javafx.iio.gif.GIFImageLoaderFactory;
import com.sun.org.apache.xalan.internal.xslt.Process;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet (urlPatterns = "/loginPage")
public class LoginPage extends HttpServlet {
    private HttpServletResponse resp;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        String user = req.getParameter("user");
        String pass = req.getParameter("pass");
        if (user.equals("C0322G1") && pass.equals("123")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/Welcome1");
            RequestDispatcher dispatcher1 = req.getRequestDispatcher("/Welcome1");
            dispatcher.forward(req, resp);
            dispatcher1.forward(req, resp);
        } else {
            printWriter.println("User va pass khong chinh xac!");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/form-login-page");
            dispatcher.include(req, resp);
            dispatcher.include(req, resp);
        }
    }
}
