package com.example.case_study_module3.dao;

import com.example.case_study_module3.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDao implements IUserDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/user_manager?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "CHUcu123456";
    private static final String INSERT_USERS_SQL = "INSERT INTO user(userName, password, phone, email, idrole) VALUES " +
            " (?, ?, ?, ?, ?);";
    private static final String SELECT_USER_BY_ID = "select idUser, userName, password, phone, email, idrole from user " +
            "where idUser =?";
    private static final String SELECT_ALL_USERS = "select * from user";
    private static final String DELETE_USERS_SQL = "delete from user where idUser = ?;";
    private static final String UPDATE_USERS_SQL = "update user set userName= ?,password= ?, phone =?, email = ?,idrole = ?  where idUser = ?;";
    private static final String SELECT_USER_BY_EMAIL = "select u.idUser, u.userName, u.password, u.phone, u.email, u.idrole\n" +
            "from user as u inner join role as r\n" +
            " where u.email = ? and u.idrole = r.id;";

    private static final String SELECT_USER_BY_PHONE = "select u.idUser, u.userName, u.password, u.phone, u.email, u.idrole\n" +
            "from user as u inner join role as r\n" +
            " where u.phone = ? and u.idrole = r.id;";
    private static final String SELECT_USER_BY_USERNAME = "select u.idUser, u.userName, u.password, u.phone, u.email, u.idrole\n" +
            "from user as u inner join role as r\n" +
            " where u.userName = ? and u.idrole = r.id;";
    private int noOfRecords;

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

    @Override
    public User selectUser(int id) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("userName");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                int idRole = Integer.parseInt(rs.getString("idrole"));
                user = new User(id, name, password, phone, email, idRole);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }

    public List<User> selectUsersPaging(int offset, int noOfRecords) {
        String query = "select SQL_CALC_FOUND_ROWS * from user limit " + offset + ", " + noOfRecords;
        List<User> list = new ArrayList<User>();
        User user = null;
        Statement stmt = null;
        Connection connection = null;
        try {
            connection = getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                user = new User();
                user.setIdUser(rs.getInt("idUser"));
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
                user.setIdrole(rs.getInt("idrole"));
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

    public List<User> selectUsersPaging(int offset, int noOfRecords, String q, int idrole) {
        List<User> list = new ArrayList<User>();
        User user = null;
        PreparedStatement stmt = null;
        Connection connection = null;
        try {
            connection = getConnection();
            if (idrole != -1) {
                String query = "select SQL_CALC_FOUND_ROWS * from user where userName like ? and idrole = ? limit "
                        + offset + ", " + noOfRecords;
                stmt = connection.prepareStatement(query);
                stmt.setString(1, '%' + q + '%');
                stmt.setInt(2, idrole);
            } else {
                if (idrole == -1) {
                    String query = "select SQL_CALC_FOUND_ROWS * from user where userName like ? limit "
                            + offset + ", " + noOfRecords;
                    stmt = connection.prepareStatement(query);
                    stmt.setString(1, '%' + q + '%');
                }
            }
            System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setIdUser(rs.getInt("idUser"));
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
                user.setIdrole(rs.getInt("idrole"));
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

    @Override
    public void insertUser(User user) throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setInt(5, user.getIdrole());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idUser");
                String name = rs.getString("userName");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                int idrole = rs.getInt("idrole");
                users.add(new User(id, name, password, phone, email, idrole));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }


    @Override
    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getPhone());
            statement.setString(4, user.getEmail());
            statement.setInt(5, user.getIdrole());
            statement.setInt(6, user.getIdUser());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        boolean flag;
        Connection connection = getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("DELETE  FROM user WHERE idUser =?;");
        preparedStatement.setInt(1, id);
        flag = preparedStatement.executeUpdate() > 0;
            connection.close();
            return flag;
    }
    public User selectUserByEmail(String emails) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL);) {
            preparedStatement.setString(1, emails);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idUser = rs.getInt("idUser");
                String userName = rs.getString("userName");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String phone= rs.getString("phone");
                int idrole = rs.getInt("idrole");
                user = new User(idUser, userName,password, phone, email,   idrole);
                return user;
            }
            return null;
        } catch (SQLException e) {
            printSQLException(e);
            return null;
        }
    }
    public User selectUserByPhone(String phone) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_PHONE);) {
            preparedStatement.setString(1, phone);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idUser");
                String name = rs.getString("userName");
                String password = rs.getString("password");
                String email = rs.getString("email");
                int idrole = rs.getInt("idrole");

                user = new User(id, name, password, phone, email, idrole);
                return user;
            }
            return null;
        } catch (SQLException e) {
            printSQLException(e);
            return null;
        }}
    public User selectUserByUserName(String userName) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME);) {
            preparedStatement.setString(1, userName);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idUser");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                int idrole = rs.getInt("idrole");

                user = new User(id, userName, password, phone, email, idrole);
                return user;
            }
            return null;
        } catch (SQLException e) {
            printSQLException(e);
            return null;
        }}



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
