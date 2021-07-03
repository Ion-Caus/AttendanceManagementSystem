package dao;

import model.StudentClass;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ClassesDAO
{
  void addClass(StudentClass aClass) throws SQLException;
  ArrayList<StudentClass> readClasses() throws SQLException;
  void removeClass(String className) throws SQLException;
}
