package dao;

import model.Class;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public interface ClassesDAO
{
  void create(String className) throws SQLException;
  List<Class> readClasses() throws SQLException;
  void update(Class someClass) throws SQLException; //not needed, check Impl class
  void delete(String className) throws SQLException;
}