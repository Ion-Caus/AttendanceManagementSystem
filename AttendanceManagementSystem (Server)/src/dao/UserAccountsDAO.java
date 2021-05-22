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
//  Student createStudent(String name, String ID) throws SQLException;
//  Teacher createTeacher(String name, String ID) throws SQLException;
//  Administrator createAdmin(String name, String ID) throws SQLException;

  // reading accounts
  ArrayList<Student> readAllStudents() throws SQLException;
  List<Teacher> readTeachers() throws SQLException;
  List<Administrator> readAdmins() throws SQLException;

  // updating accounts
   void updateUserAccount(Account account) throws SQLException;

  // deleting accounts
  void deleteUser(String userID) throws SQLException;
}
