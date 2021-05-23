package dao;

import model.Account;
import model.Administrator;
import model.Student;
import model.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserAccountsDAOImpl implements UserAccountsDAO
{
  private static UserAccountsDAOImpl instance;
  public static final String STUDENT = "student", TEACHER = "teacher", ADMIN = "admin";

  private UserAccountsDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized UserAccountsDAOImpl getInstance()
      throws SQLException
  {
    if (instance == null)
    {
      instance = new UserAccountsDAOImpl();
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

//  @Override public Student createStudent(String name, String ID)
//      throws SQLException
//  {
//    try (Connection connection = getConnection())
//    {
//      PreparedStatement statement = connection.prepareStatement(
//          "INSERT INTO user_account(userid, full_name, access) VALUES (?, ?, ?);");
//      statement.setString(1, ID);
//      statement.setString(2, name);
//      statement.setString(3, "student");
//      statement.executeUpdate();
//      return new Student(name, ID);
//    }
//  }
//
//  @Override public Teacher createTeacher(String name, String ID)
//      throws SQLException
//  {
//    try (Connection connection = getConnection())
//    {
//      PreparedStatement statement = connection.prepareStatement(
//          "INSERT INTO user_account(userid, full_name, access) VALUES (?, ?, ?);");
//      statement.setString(1, ID);
//      statement.setString(2, name);
//      statement.setString(3, "teacher");
//      statement.executeUpdate();
//      return new Teacher(name, ID);
//    }
//  }
//
//  @Override public Administrator createAdmin(String name, String ID)
//      throws SQLException
//  {
//    try (Connection connection = getConnection())
//    {
//      PreparedStatement statement = connection.prepareStatement(
//          "INSERT INTO user_account(userid, full_name, access) VALUES (?, ?, ?);");
//      statement.setString(1, ID);
//      statement.setString(2, name);
//      statement.setString(3, "admin");
//      statement.executeUpdate();
//      return new Administrator(name, ID);
//    }
//  }



  @Override public ArrayList<Student> readAllStudents() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT userID, full_name, classID from student_list full join User_account using (userID) where access='student';");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Student> result = new ArrayList<>();
      while (resultSet.next())
      {
        Student student = createStudent(resultSet);
        result.add(student);
      }
      return result;
    }
  }


  private static Student createStudent(ResultSet resultSet) throws SQLException
  {
    String name = resultSet.getString("full_name");
    String studentID = resultSet.getString("userid");
    Student student = new Student(name,studentID);
    student.setClassName(resultSet.getString("classid"));
    return student;
  }

  @Override public ArrayList<Teacher> readTeachers() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM user_account WHERE access = 'teacher';");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Teacher> result = new ArrayList<>();
      while (resultSet.next())
      {
        Teacher teacher = createTeacher(resultSet);
        result.add(teacher);
      }
      return result;
    }
  }

  public static Teacher createTeacher(ResultSet resultSet) throws SQLException
  {
    String name = resultSet.getString("full_name");
    String studentID = resultSet.getString("userid");
    return new Teacher(name, studentID);
  }

  @Override public ArrayList<Administrator> readAdmins() throws SQLException
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
          .prepareStatement("UPDATE user_account SET password = ? WHERE userid = ?");
      statement.setString(1, account.getPassword().toString());
      statement.setString(2, account.getUsername().toString());
      statement.executeUpdate();
    }
  }

  @Override public void deleteUser(String userID) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement deleteUA = connection
          .prepareStatement("DELETE FROM user_account WHERE userid = ?;");
      deleteUA.setString(1, userID);
      deleteUA.executeUpdate();
    }
  }

  @Override
  public void deleteTeacher(String teacherID) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement deleteTaught = connection
              .prepareStatement("UPDATE taught_by set teacherid=? WHERE teacherid = ?;");
      deleteTaught.setString(1, "000000");
      deleteTaught.setString(2, teacherID);
      deleteTaught.executeUpdate();
      deleteUser(teacherID);
    }
  }
}