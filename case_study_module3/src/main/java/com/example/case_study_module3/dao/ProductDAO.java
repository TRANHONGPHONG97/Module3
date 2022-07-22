package com.example.case_study_module3.dao;




import com.example.case_study_module3.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO  implements IProductDAO{
	private String jdbcURL = "jdbc:mysql://localhost:3306/user_manager?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "CHUcu123456";
	protected Connection getConnection() {

		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	    private static final String INSERT_PRODUCT_SQL = "INSERT INTO product"+ "(name, price, image ,category_id) VALUES " + " (?, ?, ?, ?);";
	    private static final String SELECT_PRODUCT_BY_ID = "select id,name, price, image ,category_id from product where id =?";
	    private static final String SELECT_ALL_PRODUCT = "select * from product";
	    private static final String DELETE_PRODUCT_SQL = "delete from product where id = ?;";
	    private static final String UPDATE_PRODUCT_SQL = "update product set name = ?,price= ?, image =?, category_id=? where id = ?;";

	    public ProductDAO() {
	    } 

	    public void insertProduct(Product product) throws SQLException {
	        System.out.println(INSERT_PRODUCT_SQL);

	        try (Connection connection = getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
	        	System.out.println(preparedStatement);  
	            preparedStatement.setString(1, product.getName());
	            preparedStatement.setFloat(2, product.getPrice());
	            preparedStatement.setString(3, product.getImage());
	            preparedStatement.setInt(4, product.getCategory_id()); 
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	    }

	    public Product selectProduct(int id) {
	    	Product product = null;

	        try ( Connection connection = getConnection();
	             
	             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);) {
	        	System.out.println(preparedStatement);  
	            preparedStatement.setInt(1, id);
	            System.out.println(preparedStatement);
	         
	            ResultSet rs = preparedStatement.executeQuery();
	            
	            while (rs.next()) {
	            	String name = rs.getString("name");
	                Float price= rs.getFloat("price");
	                String image = rs.getString("image");
	                int category_id = rs.getInt("category_id");
	                product = new Product(id, name, price, image,category_id);
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return product;
	    }

	    public List<Product> selectAllProduct() {
	        List<Product> product = new ArrayList<>();

	        try ( Connection connection = getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT);) {
	            System.out.println(preparedStatement);  
	            ResultSet rs = preparedStatement.executeQuery(); 
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("name");
	                Float price= rs.getFloat("price");
	                String image = rs.getString("image");
	                int category_id = rs.getInt("category_id");
	                product.add(new Product(id, name, price, image,category_id));
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return product;
	    }

	    public boolean deleteProduct(int id) throws SQLException {
	        boolean rowDeleted;

	        try (
					Connection connection = getConnection();
	             PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_SQL);) {
	        	System.out.println(statement);  
	            statement.setInt(1, id);
	            rowDeleted = statement.executeUpdate() > 0;
	        }
	        return rowDeleted;
	    }

	    public boolean updateProduct(Product product) throws SQLException {
	        boolean rowUpdated;

	        try ( Connection connection = getConnection();
	             PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_SQL);) {
	        	System.out.println(statement);  
	        	statement.setString(1, product.getName());
	            statement.setFloat(2, product.getPrice());
	            statement.setString(3, product.getImage());
	            statement.setInt(4, product.getCategory_id());
	            statement.setInt(5, product.getId());

	            rowUpdated = statement.executeUpdate() > 0;
	        }
	        return rowUpdated;
	    }

	    private void printSQLException(SQLException ex) {
	        for (Throwable e : ex) {
	            if (e instanceof SQLException) {
	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                System.err.println("Message: " + e.getMessage());
	                Throwable t = ex.getCause();
	                while (t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	        }
	    }
}
