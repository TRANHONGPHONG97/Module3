package com.codegym.employee.model;

public class Employee {
    private int employeeId;
    private String employeeName;
    private double salary;
    private String deptName;

    public Employee() {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.salary = salary;
        this.deptName = deptName;
    }


    public int getEmployeeId(int employee_id) {
        return employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    public String getEmployeeName(String employName) {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public double getSalary(double salary) {
        return this.salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public String getDeptName(String deptName) {
        return this.deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
