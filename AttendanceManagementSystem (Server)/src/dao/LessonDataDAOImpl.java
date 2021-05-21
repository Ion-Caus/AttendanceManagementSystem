package dao;

import model.*;
import model.Date;
import model.Time;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class LessonDataDAOImpl implements LessonDataDAO
{
  private static LessonDataDAOImpl instance;

  private LessonDataDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized LessonDataDAOImpl getInstance() throws SQLException
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

  @Override public LessonData readByStudentAndLessonID(String studentID, String lessonID)
      throws SQLException   // TODO check! not 100% sure
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM lesson_data WHERE userid = ? and lessonid = ?");
      statement.setString(1, studentID);
      statement.setString(2, lessonID);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        Grade grade = new Grade(resultSet.getInt("grade"), // change grade to String!
            resultSet.getString("comment"));
        Absence absence = new Absence(resultSet.getBoolean("absence_status"),
            resultSet.getString("absence_motive"));

        return createLessonData(resultSet);
      }
      else
      {
        return null;
      }
    }
  }

  @Override public List<LessonData> readAllByStudentID(String studentID)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM lesson_data WHERE userid = ?");
      return getLessonData(studentID, statement);
    }
  }

  @Override public List<LessonData> readAllByLessonID(String lessonID)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM lesson_data WHERE lessonid = ?");
      return getLessonData(lessonID, statement);
    }
  }

  private List<LessonData> getLessonData(String lessonID, PreparedStatement statement)
      throws SQLException
  {
    statement.setString(1, lessonID);
    ResultSet resultSet = statement.executeQuery();
    ArrayList<LessonData> result = new ArrayList<>();
    while (resultSet.next())
    {
      LessonData lessonData = createLessonData(resultSet);
      result.add(lessonData);
    }
    return result;
  }

  public static LessonData createLessonData(ResultSet resultSet)
      throws SQLException
  {
    // creating Lesson
    String teacherID = resultSet.getString("userid");
    String full_name = resultSet.getString("full_name");
    Teacher teacher = new Teacher(full_name, teacherID);
    LocalDate date = resultSet.getDate("date").toLocalDate();
    LocalTime timeTo = resultSet.getTime("timeto").toLocalTime();
    LocalTime timeFrom = resultSet.getTime("timefrom").toLocalTime();
    String subject = resultSet.getString("subject");
    String topic = resultSet.getString("topic");
    String contents = resultSet.getString("description");
    String classroom = resultSet.getString("classroom");
    String homework = resultSet.getString("homework");
    Lesson lesson = new Lesson(teacher, new Date(date), new Time(timeFrom),
        new Time(timeTo), subject, topic, contents, classroom, homework);

    // creating Student
    String userID = resultSet.getString("userid");
    String name = resultSet.getString("full_name");
    Student student = new Student(name, userID);

    return new LessonData(lesson, student);
  }

  @Override public void updateGrade(LessonData lessonData) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE lesson_data SET grade = ? WHERE userID = ? and lessonID = ?");
      statement.setInt(1, lessonData.getGrade().getGrade()); // will change to String!
      statement.setString(2, lessonData.getStudent().getID());
      statement.setString(3, lessonData.getLesson().getId());
      statement.executeUpdate();
    }
  }

  @Override public void updateComment(LessonData lessonData)
      throws SQLException  //Update statement
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE lesson_data SET comment = ? WHERE userID = ? and lessonID = ?");
      statement.setString(1, lessonData.getGrade().getComment());
      statement.setString(2, lessonData.getStudent().getID());
      statement.setString(3, lessonData.getLesson().getId());
      statement.executeUpdate();
    }
  }

  @Override public void updateAbsenceStatus(LessonData lessonData)   //Update statement for changing status to opposite
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE lesson_data SET absence_status = not absence_status WHERE userID = ? and lessonID = ?");
      statement.setString(1, lessonData.getStudent().getID());
      statement.setString(2, lessonData.getLesson().getId());
      statement.executeUpdate();
    }
  }

  @Override public void updateAbsenceMotive(LessonData lessonData)   //Update statement
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE lesson_data SET absence_motive = ? WHERE userID = ? and lessonID = ?");
      statement.setString(1, lessonData.getAbsence().getReason());
      statement.setString(2, lessonData.getStudent().getID());
      statement.setString(3, lessonData.getLesson().getId());
      statement.executeUpdate();
    }
  }

  @Override public void delete(LessonData lessonData) throws SQLException //Not sure if we need it
  {
  }
}
