package dao;

import model.Lesson;
import model.LessonData;
import model.Teacher;

import java.sql.*;

public class LessonDAOImpl implements LessonDAO
{
  private static LessonDAOImpl instance;

  private LessonDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized LessonDAOImpl getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new LessonDAOImpl();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=attendancemanagementsystem",
        "postgres", "admin");
  }



  @Override public void createLesson(Lesson lesson) throws SQLException //TODO change what we're receiving and finish corresponding inserts
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement1 = connection
          .prepareStatement("INSERT INTO lesson(subject, topic, homework, description) VALUES (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
      statement1.setString(1, lesson.getSubject());
      statement1.setString(2, lesson.getTopic());
      statement1.setString(3, lesson.getHomework());
      statement1.setString(4, lesson.getContents());
      statement1.executeUpdate();

      lesson.setId(String.valueOf(statement1.getGeneratedKeys().getInt(1)));
      System.out.println(lesson);

      PreparedStatement statement2 = connection
          .prepareStatement("INSERT INTO time_of_conduct(lessonid, date, timefrom, timeto, classroom) VALUES (?,?,?,?,?)");
      statement2.setInt(1, Integer.parseInt(lesson.getId()));
      statement2.setDate(2, Date.valueOf(lesson.getLessonDate().getDate()));
      statement2.setTime(3, Time.valueOf(lesson.getStartTime().getTime()));
      statement2.setTime(4, Time.valueOf(lesson.getEndTime().getTime()));
      statement2.setString(5, lesson.getClassroom());
      statement2.executeUpdate();

    }
  }

  @Override public void updateTeacher(Lesson lesson, Teacher teacher) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE taught_by SET teacherid = ? WHERE lessonID = ?");
      statement.setString(1, teacher.getID());
      statement.setString(2, lesson.getId());
      statement.executeUpdate();
    }
  }

  @Override public void updateTopic(Lesson lesson, String topic) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE lesson SET topic = ? WHERE lessonID = ?");
      statement.setString(1, topic);
      statement.setString(2, lesson.getId());
      statement.executeUpdate();
    }
  }

  @Override public void updateHomework(Lesson lesson, String homework) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE lesson SET homework = ? WHERE lessonID = ?");
      statement.setString(1, homework);
      statement.setString(2, lesson.getId());
      statement.executeUpdate();
    }
  }

  @Override public void updateContent(Lesson lesson, String content) throws SQLException
  {
    try (Connection connection = getConnection())
  {
    PreparedStatement statement = connection.prepareStatement(
        "UPDATE lesson SET topic = ? WHERE lessonID = ?");
    statement.setString(1, content);
    statement.setString(2, lesson.getId());
    statement.executeUpdate();
  }
  }

  @Override public void delete(Lesson lesson) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement deleteUA = connection
          .prepareStatement("DELETE FROM lesson WHERE lessonid = ?;");
      deleteUA.setString(1, lesson.getId());
      deleteUA.executeUpdate();
    }
  }
}
