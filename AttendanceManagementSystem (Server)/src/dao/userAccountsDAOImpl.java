package dao;

import model.Account;
import model.Administrator;
import model.Student;
import model.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class userAccountsDAOImpl implements userAccountsDAO
{
  private static userAccountsDAOImpl instance;

  private userAccountsDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized userAccountsDAOImpl getInstance()
      throws SQLException
  {
    if (instance == null)
    {
      instance = new userAccountsDAOImpl();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=attendancemanagementsystem",
        "postgres", "admin");
  }

  @Override public void createUserAccount(String name, String ID,
      String password, String access) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO user_account(userid, full_name, password, access) VALUES (?, ?, ?, ?);");
      statement.setString(1, ID);
      statement.setString(2, name);
      statement.setString(3, password);
      statement.setString(4, access);
      statement.executeUpdate();
    }
  }

  @Override public List<Student> readStudentByName() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM user_account WHERE access = 'student';");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Student> result = new ArrayList<>();
      while (resultSet.next())
      {
        Student student = createS(resultSet);
        result.add(student);
      }
      return result;
    }
  }

  public static Student createS(ResultSet resultSet) throws SQLException
  {
    String name = resultSet.getString("full_name");
    String studentID = resultSet.getString("userid");
    return new Student(name, studentID);
  }

  @Override public List<Teacher> readTeacherByName() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM user_account WHERE access = 'teacher';");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Teacher> result = new ArrayList<>();
      while (resultSet.next())
      {
        Teacher teacher = createT(resultSet);
        result.add(teacher);
      }
      return result;
    }
  }

  public static Teacher createT(ResultSet resultSet) throws SQLException
  {
    String name = resultSet.getString("full_name");
    String studentID = resultSet.getString("userid");
    return new Teacher(name, studentID);
  }

  @Override public List<Administrator> readAdminByName() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM user_account WHERE access = 'admin';");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Administrator> result = new ArrayList<>();
      while (resultSet.next())
      {
        Administrator admin = createA(resultSet);
        result.add(admin);
      }
      return result;
    }
  }

  public static Administrator createA(ResultSet resultSet) throws SQLException
  {
    String name = resultSet.getString("full_name");
    String studentID = resultSet.getString("userid");
    return new Administrator(name, studentID);
  }

  @Override public void updateUserAccount(Account account) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("UPDATE user_account SET password = ? WHERE id = ?");
      statement.setString(1, account.getPassword().toString());
      statement.setString(2, account.getUsername().toString());
      statement.executeUpdate();
    }
  }

  @Override public void deleteUser(String userID) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("DELETE FROM user_account WHERE userid = ?");
      statement.setString(1, userID);
      statement.executeUpdate();
    }
  }
}