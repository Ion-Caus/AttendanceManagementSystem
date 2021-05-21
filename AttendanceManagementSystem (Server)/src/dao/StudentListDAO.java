package dao;

import model.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentListDAO
{
  List<Student> readClass(String classID) throws SQLException;  //TODO list of students in exact class
  void addToClass(String classID, String userID) throws SQLException;  //TODO insert and delete statements
  void removeFromClass(String classID, String userID) throws SQLException; //could be just Class aClass and full name instead of current attributes
}
