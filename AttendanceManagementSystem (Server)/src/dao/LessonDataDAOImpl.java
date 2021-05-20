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


  //TODO need to think how are we generating initial (empty) lessonData for students on exact lessons? Maybe it should appear in another DAO or here (new statement for it is needed)



  @Override public LessonData readByStudentID(String studentID, String lessonID)  //TODO read exact student lessonData for exact lesson
      throws SQLException
  {
    return null;
  }

  @Override public List<LessonData> readAllByStudentID(String studentID)  //TODO read exact student lessonData for allLessons
      throws SQLException
  {
    return null;
  }

  @Override public List<LessonData> readByLessonID(String lessonID)    //TODO read all students lessonData for columns (Teacher view)

  throws SQLException
  {
    return null;
  }

  @Override public void updateGrade(LessonData lessonData) throws SQLException  // //could be done with comment, gotta see how we implement it in the app
  {

  }

  @Override public void updateComment(LessonData lessonData) throws SQLException  //Update statement
  {

  }

  @Override public void updateAbsenceStatus(LessonData lessonData)   //Update statement
      throws SQLException
  {

  }

  @Override public void updateAbsenceMotive(LessonData lessonData)   //Update statement
      throws SQLException
  {

  }

  @Override public void delete(LessonData lessonData) throws SQLException //Not sure if we need it
  {

  }
}
