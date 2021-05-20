package dao;

import model.Class;
import model.Lesson;
import model.Schedule;
import model.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDAOImpl implements ScheduleDAO
{
  private static ScheduleDAOImpl instance;

  private ScheduleDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized ScheduleDAOImpl getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new ScheduleDAOImpl();
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

/*  @Override public void create(Class className) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE classes set scheduleID=(?) where classID=(?);");
      statement.setString(1, className.getSchedule()
          .getScheduleID()); //TODO get scheduleID from object
      statement.setString(2, className.getClassName());
      statement.executeUpdate();
    }
  }*/

  public static Lesson createLesson(ResultSet resultSet) throws SQLException
  {
    String lessonID = resultSet.getString("lessonid");
    Teacher Teacher = new Teacher(resultSet.getString("full_name"), resultSet.getString("teacherid"));
    Date date = resultSet.getDate("date");
    Time time1 = resultSet.getTime("timefrom");
    Time time2 = resultSet.getTime("timeto");
    String subject = resultSet.getString("subject");
    String topic = resultSet.getString("topic");
    String classroom = resultSet.getString("classroom");
    String homework = resultSet.getString("homework");
    return new Lesson(lessonID, Teacher, date, time1, time2, subject, topic, classroom, homework);
  }

  @Override public List<Lesson> readLessons(Class aClass, Date date) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT lessonID FROM schedule_lessons join time_of_conduct using (lessonID) where classID = (?) and date = (?)");
      statement.setString(1, aClass.getClassName());
      statement.setString(2, String.valueOf(date));
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Lesson> result = new ArrayList<>();
      while (resultSet.next())
      {
        Lesson lesson = createLesson(resultSet);
        result.add(lesson);
      }
      return result;
    }
  }


  @Override public List<Lesson> readLessonsByTeacherName(Teacher teacher) throws SQLException  //searches by name, but also could replace it with id
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT lessonID FROM taught_by join User_account on userID = Taught_by.teacherID) where full_name = (?)");
      statement.setString(1, teacher.getName());
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Lesson> result = new ArrayList<>();
      while (resultSet.next())
      {
        Lesson lesson = createLesson(resultSet);
        result.add(lesson);
      }
      return result;
    }
  }

  @Override public void update(String scheduleID) throws SQLException
  {

  }

  @Override public void addLesson(Class aClass, String lessonID) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("INSERT INTO schedule_lessons VALUES (?,?)");
      statement.setString(1, aClass.getClassName());
      statement.setString(2, lessonID);
      statement.executeUpdate();
    }
  }

  @Override public void deleteLesson(String lessonID, Class aClass) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("DELETE FROM schedule_lessons WHERE lessonID = ? and classID = ?");
      statement.setString(1, lessonID);
      statement.setString(2, aClass.getClassName());
      statement.executeUpdate();
    }
  }
}
