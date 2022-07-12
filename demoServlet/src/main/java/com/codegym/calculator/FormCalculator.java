package com.codegym.calculator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet (urlPatterns = "/Form-Calculator")
public class FormCalculator extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<html>\n" +
                "<head>\n" +
                "    <title>Product Discount Calculator</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div id=\"content\">\n" +
                "    <h1>Product Discount Calculator</h1>\n" +
                "    <form method=\"post\" action=\"/servlet-demo\">\n" +
                "        <div id=\"data\">\n" +
                "            <label>Product Description:</label>\n" +
                "            <input type=\"text\" name=\"description\"/><br/>\n" +
                "            <label>List Price:</label>\n" +
                "            <input type=\"text\" name=\"price\"/><br/>\n" +
                "            <label>Discount Percent:</label>\n" +
                "            <input type=\"text\" name=\"discount_percent\"/>(%)<br/>\n" +
                "        </div>\n" +
                "        <div id=\"buttons\">\n" +
                "            <label>&nbsp;</label>\n" +
                "            <input type=\"submit\" value=\"Calculate Discount\"/>\n" +
                "        </div>\n" +
                "    </form>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
