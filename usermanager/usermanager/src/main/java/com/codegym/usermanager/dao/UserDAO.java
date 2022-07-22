package com.codegym.usermanager.dao;

import com.codegym.usermanager.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/usermanager?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "CHUcu123456";
    private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (name, email, idcountry, password,  urlimage) VALUES " +
            " (?, ?, ?, ?, ?);";
    private static final String SELECT_USER_BY_ID = "select id,name,email,idcountry from users where id =?";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
    private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, idcountry =?, password = ?  where id = ?;";
    private static final String SELECT_USER_BY_EMAIL = "select u.id,u.name,u.email, u.idcountry\r\n"
            + "    		 from users as u inner join country as c\r\n"
            + "    		where u.email = ? and u.idcountry = c.id;";
    private int noOfRecords;
    public UserDAO() {
    }
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

    public User selectUserByEmail(String _email) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL);) {
            preparedStatement.setString(1, _email);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                int idCountry = rs.getInt("idcountry");
                user = new User(id, name, email, idCountry);
                return user;
            }
            return null;
        } catch (SQLException e) {
            printSQLException(e);
            return null;
        }
    }

    public void insertUser(User user) throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setInt(3, user.getIdcountry());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getUrlImage());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public User selectUser(int id) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                int idcountry = Integer.parseInt(rs.getString("idcountry"));
                user = new User(id, name, email, idcountry);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }

    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                int idcountry = Integer.parseInt(rs.getString("idcountry"));
                String password = rs.getString("password");
                users.add(new User(id, name, email, idcountry, password));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

      public List<User> selectUsersPaging(int offset, int noOfRecords, String q, int idcountry) {
        String query = "select SQL_CALC_FOUND_ROWS * from users limit " + offset + ", " + noOfRecords;
        List<User> list = new ArrayList<User>();
        User user = null;
        Statement stmt = null;
        Connection connection = null    ;
        try {
           connection = getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setIdcountry(rs.getInt("idcountry"));
                list.add(user);
            }
            rs.close();

            rs = stmt.executeQuery("SELECT FOUND_ROWS()");
            if (rs.next())
                this.noOfRecords = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public int getNoOfRecords() {
        return noOfRecords;
    }
    public List<User> selectUsersPagging(int offset, int noOfRecords, String q, int idcountry) {
        List<User> list = new ArrayList<User>();
        User user = null;
        PreparedStatement stmt = null;
        Connection connection = null    ;
        try {
            connection = getConnection();
            if (idcountry !=-1){
                String query = "select SQL_CALC_FOUND_ROWS * from users where name like ? and idcountry = ? limit "
                        + offset + ", " + noOfRecords;
                stmt = connection.prepareStatement(query);
                stmt.setString(1, '%' + q+ '%');
                stmt.setInt(2, idcountry);
            } else {
                if (idcountry == -1){
                    String query = "select SQL_CALC_FOUND_ROWS * from users where name like ? limit "
                            + offset + ", " + noOfRecords;
                    stmt = connection.prepareStatement(query);
                    stmt.setString(1, '%' + q+ '%');
                }
            }
            System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setIdcountry(rs.getInt("idcountry"));
                list.add(user);
            }
            rs.close();

            rs = stmt.executeQuery("SELECT FOUND_ROWS()");
            if (rs.next())
                this.noOfRecords = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setInt(3, user.getIdcountry());
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getId());
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
