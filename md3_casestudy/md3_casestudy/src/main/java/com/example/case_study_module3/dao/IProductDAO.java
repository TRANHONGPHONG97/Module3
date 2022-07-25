package com.example.case_study_module3.dao;

import com.example.case_study_module3.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductDAO {
    public boolean insertProduct(Product product) throws SQLException;
    public Product selectProduct(int id);
    public List<Product> selectAllProduct();
    public boolean updateProduct(Product product) throws SQLException;
    boolean deleteProduct (int id) throws SQLException;
}
