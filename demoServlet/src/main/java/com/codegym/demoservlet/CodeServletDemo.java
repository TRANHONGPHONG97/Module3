package com.codegym.demoservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet (name = "codeServletDemo", urlPatterns = "/codeservletdemo")
public class CodeServletDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
//        100 - tiếp tục,
//        200 - request OK, trả về thành công
//        301 - chuyển hướng (có 1 đường dẫn chuyển sang đường dẫn khác)
//        401 - yêu cầu xác thực
//        403 - truy cập tài nguyên bị hạn chế
//        404 - đường dẫn không tìm thấy
//        500 - server trả về lỗi không mong đợi
        resp.sendError(404);
//        resp.sendError(200);
//        resp.sendError(301);
//        resp.sendError(401);
//        resp.sendError(404);
//        resp.sendError(500);
//        PrintWriter printWriter = resp.getWriter();
//        printWriter.println("C0322G1");
    }
}
