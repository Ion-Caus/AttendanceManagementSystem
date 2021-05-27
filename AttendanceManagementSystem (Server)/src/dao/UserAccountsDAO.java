package dao;

import model.Account;
import model.Administrator;
import model.Student;
import model.Teacher;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserAccountsDAO
{
  // creating accounts
  void createUserAccount(String name, String ID, String password, String access) throws SQLException;

  // reading accounts
  ArrayList<Student> readAllStudents() throws SQLException;
  ArrayList<Teacher> readTeachers() throws SQLException;
  ArrayList<Administrator> readAdmins() throws SQLException;

  // updating accounts
   void updateUserAccount(Account account) throws SQLException;

  // deleting accounts
  void deleteUser(String userID) throws SQLException;
  void deleteTeacher(String teacherID) throws SQLException;

  // login
  String login(String username, String password) throws SQLException, IllegalAccessException;

}
