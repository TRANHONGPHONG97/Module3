package com.example.case_study_module3.dao;

import com.example.case_study_module3.model.Category;

import java.sql.SQLException;
import java.util.List;

public interface ICategoryDAO {
    public void insertCategory(Category category) throws SQLException;
    public Category selectCategory(int id);
    public List<Category> selectAllCategory();
    public boolean updateCategory(Category category) throws SQLException;
    boolean deleteCategory (int id) throws SQLException;
}
