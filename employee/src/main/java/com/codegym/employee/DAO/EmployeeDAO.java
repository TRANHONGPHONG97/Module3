package com.codegym.employee.DAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.codegym.employee.db.ConnectionFactory;
import com.codegym.employee.model.Employee;
public class EmployeeDAO {

    Connection connection;
    Statement stmt;
    private int noOfRecords;

    public EmployeeDAO() { }

    private static Connection getConnection()
            throws SQLException,
            ClassNotFoundException
    {
        Connection con = ConnectionFactory.
                getInstance().getConnection();
        return con;
    }

    public List<Employee> viewAllEmployees(
            int offset,
            int noOfRecords)
    {
        String query = "select SQL_CALC_FOUND_ROWS * from employee limit "
                + offset + ", " + noOfRecords;
        List<Employee> list = new ArrayList<Employee>();
        Employee employee = null;
        try {
            connection = getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                employee = new Employee();
                employee.getEmployeeId(rs.getInt("employee_Id"));
                employee.getEmployeeName(rs.getString("employName"));
                employee.getSalary(rs.getDouble("salary"));
                employee.getDeptName(rs.getString("deptName"));
                list.add(employee);
//                int employee_id = rs.getInt("employee_Id");
//                String employeeName = rs.getString("employeeName");
//                String salary = rs.getString("salary");
//                String deptName = rs.getString("deptName");
////                int idcountry = Integer.parseInt(rs.getString("idcountry"));
//                list.add(new Employee(employee_id,employeeName, salary, deptName));
            }
            rs.close();

            rs = stmt.executeQuery("SELECT FOUND_ROWS()");
            if(rs.next())
                this.noOfRecords = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally
        {
            try {
                if(stmt != null)
                    stmt.close();
                if(connection != null)
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
}
