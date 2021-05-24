package dao;

import model.Class;
import model.Lesson;
import model.Teacher;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ScheduleDAOImpl implements ScheduleDAO {
    private static ScheduleDAOImpl instance;

    private ScheduleDAOImpl() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public static synchronized ScheduleDAOImpl getInstance() throws SQLException {
        if (instance == null) {
            instance = new ScheduleDAOImpl();
        }
        return instance;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres?currentSchema=attendance_management_system",
                "postgres", "admin");
        //TODO change password to your local database password for it to work
    }

    private static Lesson createLesson(ResultSet resultSet) throws SQLException
    {
        String id = resultSet.getString("lesson_id");
        Teacher teacher = new Teacher(resultSet.getString("full_name"), resultSet.getString("user_id"));
        LocalDate date = resultSet.getDate("lesson_date").toLocalDate();
        LocalTime time1 = resultSet.getTime("time_from").toLocalTime();
        LocalTime time2 = resultSet.getTime("time_to").toLocalTime();
        String subject = resultSet.getString("subject");
        String topic = resultSet.getString("topic");
        String contents = resultSet.getString("description");
        String classroom = resultSet.getString("classroom");
        String homework = resultSet.getString("homework");
        String className = resultSet.getString("class_name");
        return new Lesson(id, teacher, new model.Date(date), new model.Time(time1),
                new model.Time(time2), subject, topic, contents, classroom, homework, className);
    }


    @Override
    public ArrayList<Lesson> readLessons(Class aClass, LocalDate lessonDate) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT class_name, lesson_id, full_name, user_id, lesson_date, time_from, time_to, subject, topic, description, classroom, homework FROM lesson join time_of_conduct using (lesson_id) join taught_by using (lesson_id) join user_account on taught_by.teacher_id = User_account.user_id join schedule_lessons using (lesson_id) where class_name = ? and lesson_date=?");
            statement.setString(1, aClass.getClassName());
            statement.setDate(2, Date.valueOf(lessonDate));
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Lesson> result = new ArrayList<>();
            while (resultSet.next()) {
                Lesson lesson = createLesson(resultSet);
                result.add(lesson);
            }
            return result;
        }
    }


    @Override
    public ArrayList<Lesson> readAll() throws SQLException {
        try (Connection connection = getConnection()) {

            PreparedStatement statement = connection
                    .prepareStatement("SELECT class_name, lesson_id, full_name, user_id, lesson_date, time_from, time_to, subject, topic, description, classroom, homework FROM lesson " +
                            "join time_of_conduct using (lesson_id) " +
                            "join taught_by using (lesson_id) " +
                            "join user_account on taught_by.teacher_id = user_account.user_id " +
                            "join schedule_lessons using (lesson_id)"
                    );
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Lesson> result = new ArrayList<>();
            while (resultSet.next()) {
                Lesson lesson = createLesson(resultSet);
                result.add(lesson);
            }
            return result;
        }
    }

    @Override
    public ArrayList<Lesson> readLessonsByTeacherName(Teacher teacher) throws SQLException  //searches by name, but also could replace it with id
    {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT full_name, user_id, lesson_date, time_from, time_to, subject, topic, description, classroom, homework FROM lesson join time_of_conduct using (lesson_id) join taught_by using (lesson_id) join user_account on taught_by.teacher_id = user_account.user_id join schedule_lessons using (lesson_id) where full_name = ?");
            statement.setString(1, teacher.getName());
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Lesson> result = new ArrayList<>();
            while (resultSet.next()) {
                Lesson lesson = createLesson(resultSet);
                result.add(lesson);
            }
            return result;
        }
    }

    @Override
    public void addLesson(Class aClass, String lessonID) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO schedule_lessons VALUES (?,?)");
            statement.setString(1, aClass.getClassName());
            statement.setString(2, lessonID);
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteLesson(String lessonID, Class aClass) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("DELETE FROM schedule_lessons WHERE lesson_id = ? and class_name = ?");
            statement.setString(1, lessonID);
            statement.setString(2, aClass.getClassName());
            statement.executeUpdate();
        }
    }
}
