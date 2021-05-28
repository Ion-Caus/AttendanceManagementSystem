package dao;

import model.Administrator;
import model.Student;
import model.Teacher;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserAccountsDAO
{
  // creating accounts
  void createUserAccount(String name, String ID, String password, String access) throws SQLException;

  // reading accounts
  ArrayList<Student> readAllStudents() throws SQLException;
  ArrayList<Teacher> readTeachers() throws SQLException;
  ArrayList<Administrator> readAdmins() throws SQLException;

  // updating accounts
   void updatePassword(String userID, String password) throws SQLException;

  // deleting accounts
  void deleteUser(String userID) throws SQLException;
  void deleteTeacher(String teacherID) throws SQLException;

  // login
  String login(String username, String password) throws SQLException, IllegalAccessException;

}
