package dao;

import model.Class;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassesDAOImpl implements ClassesDAO
{
  private static ClassesDAOImpl instance;

  private ClassesDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized ClassesDAOImpl getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new ClassesDAOImpl();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=attendancemanagementsystem",
        "postgres", "admin");
    //TODO change password to your local database password for it to work
  }


  @Override public void create(String className) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("INSERT INTO classes(classID) VALUES (?);");
      statement.setString(1, className);
      statement.executeUpdate();
    }
  }

  public static Class createClass(ResultSet resultSet) throws SQLException
  {
    String classID = resultSet.getString("classid");
    return new Class(classID);
  }

  @Override public List<Class> readClasses() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT distinct classID FROM Classes");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Class> result = new ArrayList<>();
      while (resultSet.next())
      {
        Class aClass = createClass(resultSet);
        result.add(aClass);
      }
      return result;
    }
  }

  @Override public void update(Class someClass)
      throws SQLException  //not needed since we're not editing "class names"
  {

  }

  @Override public void delete(String className) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("DELETE FROM classes WHERE classID = ?");
      statement.setString(1, className);
      statement.executeUpdate();
    }
  }
}