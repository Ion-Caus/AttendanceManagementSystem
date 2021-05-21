package dao;

import model.Class;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public interface ClassesDAO
{
  void addClass(Class aClass) throws SQLException;
  List<Class> readClasses() throws SQLException;
  void removeClass(String className) throws SQLException;
}
