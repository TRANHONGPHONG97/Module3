package com.example.case_study_module3.dao;

import com.example.case_study_module3.model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDao {
    public void insertUser(User user) throws SQLException;
    public User selectUser(int id);
    public List<User> selectAllUsers();
    public boolean updateUser(User user) throws SQLException;
boolean deleteUser (int id) throws SQLException;

}
