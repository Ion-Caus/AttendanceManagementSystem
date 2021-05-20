package dao;


import model.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class StudentListDAOImpl implements StudentListDAO
{
  private static StudentListDAOImpl instance;

  private StudentListDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized StudentListDAOImpl getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new StudentListDAOImpl();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=attendancemanagementsystem",
        "postgres", "admin");
  }

  @Override public List<Student> readClass(String classID) throws SQLException
  {
    return null;
  }

  @Override public void addToClass(String classID, String userID)
      throws SQLException
  {

  }

  @Override public void removeFromClass(String classID, String userID)
      throws SQLException
  {

  }
  // TODO
}
