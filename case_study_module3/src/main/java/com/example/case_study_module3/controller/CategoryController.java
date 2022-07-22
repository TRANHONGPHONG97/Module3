package com.example.case_study_module3.controller;


import com.example.case_study_module3.dao.CategoryDAO;
import com.example.case_study_module3.model.Category;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/controller")
public class CategoryController extends HttpServlet {
       private CategoryDAO categoryDAO;
       
   
    public CategoryController() {
        super();
         
    }
	 public void init() {
		 categoryDAO=new CategoryDAO();
	 } 

	 
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        doGet(request, response);
	    }

	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String action = request.getServletPath();

	        try {
	            switch (action) {
	                case "/newCategory":
	                    showNewForm(request, response);
	                    break;
	                case "/insertCategory":
	                    insertCategory(request, response);
	                    break;
	                case "/deleteCategory":
	                    deleteCategory(request, response);
	                    break;
	                case "/editCategory":
	                    showEditForm(request, response);
	                    break;
	                case "/updateCategory":
	                    updateCategory(request, response);
	                    break;
	                default:
	                    listCategory(request, response);
	                    break;
	            }
	        } catch (SQLException ex) {
	            throw new ServletException(ex);
	        }
	    }

	    private void listCategory(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	    	
	        List<Category> listCategory = categoryDAO.selectAllCategory();
	        request.setAttribute("listCategory", listCategory);
	        
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/HomePage.jsp");
	        dispatcher.forward(request, response);
	    }

	    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/user-form.jsp");
	        dispatcher.forward(request, response);
	    }

	    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, ServletException, IOException { 
	    	 
	        
	        int id = Integer.parseInt(request.getParameter("id"));
	        Category existingCategory = categoryDAO.selectCategory(id);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/user-form.jsp");
	        request.setAttribute("category", existingCategory);
	        dispatcher.forward(request, response);

	    }

	    private void insertCategory(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException {
	        String name = request.getParameter("name");  
	        Category newCategory = new Category(name );
	        categoryDAO.insertCategory(newCategory);
	        response.sendRedirect("list");
	    }

	    private void updateCategory(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException {
	        int id = Integer.parseInt(request.getParameter("id"));
	        String name = request.getParameter("name"); 
	        

	        Category category = new Category(id, name );
	        categoryDAO.updateCategory(category);
	        response.sendRedirect("list");
	    }

	    private void deleteCategory(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException {
	        int id = Integer.parseInt(request.getParameter("id"));
	        categoryDAO.deleteCategory(id);
	        response.sendRedirect("list");

	    }
}
