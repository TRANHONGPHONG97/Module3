package com.example.case_study_module3.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/assetss/*")
public class ResourceFilter extends HttpFilter {
    public ResourceFilter() {
        super();
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
//        super.doFilter(req, res, chain);
        String pathInfo =req.getPathInfo();
        String pathServlet = req.getServletPath();
        String pathURI = req.getRequestURI();
        System.out.println("Info  " + pathInfo);
        System.out.println("Servlet " + pathServlet);
        System.out.println("URI "+ pathURI);

        if (pathURI.equals("/assetss/*")){
            res.sendError(404);
        }else {
            chain.doFilter(req,res);
        }
    }
}
