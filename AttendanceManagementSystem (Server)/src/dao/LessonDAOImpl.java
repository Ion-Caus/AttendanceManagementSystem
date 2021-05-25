package dao;

import model.Class;
import model.Lesson;
import model.LessonData;
import model.Teacher;

import java.sql.*;

public class LessonDAOImpl implements LessonDAO {
    private static LessonDAOImpl instance;

    private LessonDAOImpl() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public static synchronized LessonDAOImpl getInstance() throws SQLException {
        if (instance == null) {
            instance = new LessonDAOImpl();
        }
        return instance;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres?currentSchema=attendance_management_system",
                "postgres", "admin");
    }


    @Override
    public void createLesson(Class aClass, Lesson lesson) throws SQLException
    {
        try (Connection connection = getConnection()) {
            PreparedStatement statement1 = connection
                    .prepareStatement("INSERT INTO lesson(subject, topic, homework, description) VALUES (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            statement1.setString(1, lesson.getSubject());
            statement1.setString(2, lesson.getTopic());
            statement1.setString(3, lesson.getHomework());
            statement1.setString(4, lesson.getContents());
            statement1.executeUpdate();

            ResultSet generatedKeys = statement1.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new SQLException("No keys generated");
            }
            int lessonID = generatedKeys.getInt(1);
            lesson.setId(String.valueOf(lessonID));

            PreparedStatement statement2 = connection
                    .prepareStatement("INSERT INTO time_of_conduct(lesson_id, lesson_date, time_from, time_to, classroom) VALUES (?,?,?,?,?)");
            statement2.setInt(1, Integer.parseInt(lesson.getId()));
            statement2.setDate(2, Date.valueOf(lesson.getLessonDate()));
            statement2.setTime(3, Time.valueOf(lesson.getStartTime()));
            statement2.setTime(4, Time.valueOf(lesson.getEndTime()));
            statement2.setString(5, lesson.getClassroom());
            statement2.executeUpdate();

            PreparedStatement statement3 = connection
                    .prepareStatement("INSERT INTO taught_by(teacher_id, lesson_id) VALUES (?,?)");
            statement3.setString(1, lesson.getTeacher().getID());
            statement3.setInt(2, Integer.parseInt(lesson.getId()));
            statement3.executeUpdate();

            PreparedStatement statement4 = connection
                    .prepareStatement("INSERT INTO schedule_lessons(class_name, lesson_id) VALUES (?,?)");
            statement4.setString(1, aClass.getClassName());
            statement4.setInt(2, Integer.parseInt(lesson.getId()));
            statement4.executeUpdate();
        }
    }
    @Override
    public void updateLesson(Lesson lesson, Teacher teacher, String topic, String contents, String homework) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE taught_by SET teacher_id = ? WHERE lesson_id = ?");
            statement.setString(1, teacher.getID());
            statement.setInt(2, Integer.parseInt(lesson.getId()));
            statement.executeUpdate();

            PreparedStatement statement2 = connection.prepareStatement(
                    "UPDATE lesson SET topic = ?, description = ?, homework = ? WHERE lesson_id = ?");
            statement2.setString(1, topic);
            statement2.setString(2, contents);
            statement2.setString(3, homework);
            statement2.setInt(4, Integer.parseInt(lesson.getId()));
            statement2.executeUpdate();
        }
    }

    @Override
    public void updateTeacher(Lesson lesson, Teacher teacher) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE taught_by SET teacher_id = ? WHERE lesson_id = ?");
            statement.setString(1, teacher.getID());
            statement.setInt(2, Integer.parseInt(lesson.getId()));
            statement.executeUpdate();

        }
    }

    @Override
    public void updateTopic(Lesson lesson, String topic) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE lesson SET topic = ? WHERE lesson_id = ?");
            System.out.println(topic);
            statement.setString(1, topic);
            statement.setInt(2, Integer.parseInt(lesson.getId()));
            statement.executeUpdate();
        }
    }

    @Override
    public void updateHomework(Lesson lesson, String homework) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE lesson SET homework = ? WHERE lesson_id = ?");
            statement.setString(1, homework);
            statement.setInt(2, Integer.parseInt(lesson.getId()));
            statement.executeUpdate();
        }
    }

    @Override
    public void updateContent(Lesson lesson, String content) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE lesson SET topic = ? WHERE lesson_id = ?");
            statement.setString(1, content);
            statement.setInt(2, Integer.parseInt(lesson.getId()));
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(String lessonID) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement deleteUA = connection
                    .prepareStatement("DELETE FROM lesson WHERE lesson_id = ?;");
            deleteUA.setInt(1, Integer.parseInt(lessonID));
            deleteUA.executeUpdate();
        }
    }
}
