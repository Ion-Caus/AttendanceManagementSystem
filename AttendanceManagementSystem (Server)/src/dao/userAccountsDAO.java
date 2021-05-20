package dao;

import model.Administrator;
import model.Student;
import model.Teacher;

import java.sql.SQLException;
import java.util.List;

public interface userAccountsDAO
{
  // creating accounts
  Student createStudent(String name, String ID) throws SQLException;
  Teacher createTeacher(String name, String ID) throws SQLException;
  Administrator createAdmin(String name, String ID) throws SQLException;

//  Student readById(String ID) throws SQLException;
//  Teacher readById(String ID) throws SQLException;
//  Administrator readById(String ID) throws SQLException;

  // reading accounts
  List<Student> readStudentByName() throws SQLException;
  List<Teacher> readTeacherByName() throws SQLException;
  List<Administrator> readAdminByName() throws SQLException;

//   void update(Student student) throws SQLException;
//   void update(Teacher teacher) throws SQLException;
//   void update(Administrator admin) throws SQLException;

  // deleting accounts
  void deleteUser(String userID) throws SQLException;
}
