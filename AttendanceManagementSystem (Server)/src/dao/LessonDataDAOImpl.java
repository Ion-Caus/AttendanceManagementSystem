package dao;

import model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class LessonDataDAOImpl implements LessonDataDAO
{
  private static LessonDataDAOImpl instance;

  private LessonDataDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized LessonDataDAOImpl getInstance()
      throws SQLException
  {
    if (instance == null)
    {
      instance = new LessonDataDAOImpl();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=attendancemanagementsystem",
        "postgres", "admin");
  }

  @Override public LessonData readByUserAndLessonID(String userID,
      String lessonID) throws SQLException
  {
    return null;
  }

  @Override public List<LessonData> readByStudentID(String studentID)
      throws SQLException
  {
    return null;
  }

  @Override public List<LessonData> readByLessonID(String lessonID)
      throws SQLException
  {
    return null;
  }

  @Override public void update(LessonData lessonData) throws SQLException
  {

  }

  @Override public void delete(LessonData lessonData) throws SQLException
  {

  }
}
