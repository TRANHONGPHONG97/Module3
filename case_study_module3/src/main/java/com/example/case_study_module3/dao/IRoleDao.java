package com.example.case_study_module3.dao;

import com.example.case_study_module3.model.Role;

import java.sql.SQLException;
import java.util.List;

public interface IRoleDao {
    public void insertRole(Role role) throws SQLException;
    public Role selectRole(int id);
    public List<Role> selectAllRole();
    public boolean deleteRole(int id) throws SQLException;
    public boolean updateCountry(Role role) throws SQLException;
}
