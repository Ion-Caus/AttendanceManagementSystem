package dao;

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

  @Override public Student createStudent(String name, String ID)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO user_account(userid, full_name, access) VALUES (?, ?, ?);");
      statement.setString(1, ID);
      statement.setString(2, name);
      statement.setString(3, "student");
      statement.executeUpdate();
      return new Student(name, ID);
    }
  }

  @Override public Teacher createTeacher(String name, String ID)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO user_account(userid, full_name, access) VALUES (?, ?, ?);");
      statement.setString(1, ID);
      statement.setString(2, name);
      statement.setString(3, "teacher");
      statement.executeUpdate();
      return new Teacher(name, ID);
    }
  }

  @Override public Administrator createAdmin(String name, String ID)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO user_account(userid, full_name, access) VALUES (?, ?, ?);");
      statement.setString(1, ID);
      statement.setString(2, name);
      statement.setString(3, "admin");
      statement.executeUpdate();
      return new Administrator(name, ID);
    }
  }

  //    @Override public Student readById(String id) throws SQLException
  //    {
  //      try (Connection connection = getConnection())
  //      {
  //        PreparedStatement statement = connection
  //            .prepareStatement("SELECT * FROM user_account WHERE userid = ?");
  //        statement.setString(1, id);
  //        ResultSet resultSet = statement.executeQuery();
  //        if (resultSet.next())
  //        {
  //          String studentName = resultSet.getString("full_name");
  //          return createStudent(resultSet);
  //        }
  //        else
  //        {
  //          return null;
  //        }
  //      }
  //    }

  @Override public List<Student> readStudentByName() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM user_account");
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
          .prepareStatement("SELECT * FROM user_account");
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
          .prepareStatement("SELECT * FROM user_account");
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

  //  @Override public void update(Student student) throws SQLException
  //  {
  //
  //  }

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

  // TODO need to also add Passwords to the creation of userAccounts
}