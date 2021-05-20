package dao;

import model.Account;
import model.Administrator;
import model.Student;
import model.Teacher;

import java.sql.SQLException;
import java.util.List;

public interface userAccountsDAO
{
  // creating accounts
  void createUserAccount(String name, String ID, String password, String access) throws SQLException;

  // reading accounts
  List<Student> readStudentByName() throws SQLException;
  List<Teacher> readTeacherByName() throws SQLException;
  List<Administrator> readAdminByName() throws SQLException;

  // updating accounts
   void updateUserAccount(Account account) throws SQLException;

  // deleting accounts
  void deleteUser(String userID) throws SQLException;
}
