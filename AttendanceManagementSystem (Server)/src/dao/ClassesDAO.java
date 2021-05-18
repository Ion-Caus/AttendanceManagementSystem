package dao;

import model.Class;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public interface ClassesDAO
{
  Class create(String className) throws SQLException;
  List<Class> readClasses() throws SQLException;
  void update(Class someClass) throws SQLException; //not needed, check Impl class
  Class delete(String className) throws SQLException;
}
