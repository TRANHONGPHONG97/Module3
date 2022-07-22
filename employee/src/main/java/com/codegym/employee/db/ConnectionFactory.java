package com.codegym.employee.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionFactory {
    //static reference to itself
    private static ConnectionFactory instance =
            new ConnectionFactory();
    String url = "jdbc:mysql://localhost:3306/employee?useSSL=false";
    String user = "root";
    String password = "CHUcu123456";
    String driverClass = "com.mysql.jdbc.Driver";


    private ConnectionFactory() {
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionFactory getInstance() {
        return instance;
    }

    public Connection getConnection() throws SQLException,
            ClassNotFoundException {
        Connection connection =
                DriverManager.getConnection(url, user, password);
        return connection;
    }
}
//public Connection getConnection() {
//        Connection connection = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return connection;
//    }
//}
