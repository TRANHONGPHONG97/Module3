package com.codegym.calculator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/servlet-demo"})
public class DiscountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        float price = Float.parseFloat(req.getParameter("price"));
        float percent = Float.parseFloat(req.getParameter("discount_percent"));
        float amount = (float) (price * percent * 0.01);
        req.setAttribute("price", price);
        req.setAttribute("percent", percent);
        req.setAttribute("amount", amount);
        RequestDispatcher dispatcher = req.getRequestDispatcher("display_discount.jsp");
        dispatcher.forward(req, resp);

    }
}
