package dao;

import model.*;
import model.Date;
import model.Time;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class LessonDataDAOImpl implements LessonDataDAO {
    private static LessonDataDAOImpl instance;

    private LessonDataDAOImpl() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public static synchronized LessonDataDAOImpl getInstance() throws SQLException {
        if (instance == null) {
            instance = new LessonDataDAOImpl();
        }
        return instance;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres?currentSchema=attendance_management_system",
                "postgres", "admin");
    }

    @Override
    public void createLessonData(String lessonID, String studentID) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO lesson_data(user_id, lesson_id, absence_status,grade) VALUES (?,?,?,?)");
            statement.setString(1, studentID);
            statement.setInt(2, Integer.parseInt(lessonID));
            statement.setBoolean(3, false);
            statement.setInt(4,-1);
            statement.executeUpdate();
        }
    }

    @Override
    public LessonData readByStudentAndLessonID(String studentID, String lessonID) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM lesson_data join schedule_lessons sl on lesson_data.lesson_id = sl.lesson_id WHERE user_id = ? and lesson_data.lesson_id = ?");
            statement.setString(1, studentID);
            statement.setString(2, lessonID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createLessonDataResultSet(resultSet);
            }
            return null;
        }
    }


    @Override
    public ArrayList<LessonData> readAll() throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement(
                            "SELECT * FROM lesson_data " +
                            "join user_account using(user_id) " +
                            "join lesson using(lesson_id) " +
                            "join time_of_conduct using(lesson_id) " +
                            "join schedule_lessons using(lesson_id);"
                    );
            ResultSet resultSet = statement.executeQuery();
            ArrayList<LessonData> result = new ArrayList<>();
            while (resultSet.next()) {
                LessonData lessonData = createLessonDataResultSet(resultSet);
                result.add(lessonData);
            }
            return result;
        }
    }

    @Override
    public ArrayList<LessonData> readAllByStudentID(String studentID)
            throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM lesson_data ld join user_account ua on ua.user_id = ld.user_id " +
                            "join schedule_lessons sl on ld.lesson_id = sl.lesson_id WHERE ld.user_id = ?");
            return getLessonData(studentID, statement);
        }
    }

    @Override
    public ArrayList<LessonData> readAllByLessonID(String lessonID)
            throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM lesson_data join user_account ua on ua.user_id = lesson_data.user_id " +
                            "join schedule_lessons sl on lesson_data.lesson_id = sl.lesson_id WHERE lesson_data.lesson_id = ?");
            return getLessonData(lessonID, statement);
        }
    }

    private ArrayList<LessonData> getLessonData(String lessonID, PreparedStatement statement)
            throws SQLException {
        statement.setString(1, lessonID);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<LessonData> result = new ArrayList<>();
        while (resultSet.next()) {
            LessonData lessonData = createLessonDataResultSet(resultSet);
            result.add(lessonData);
        }
        return result;
    }

    private static LessonData createLessonDataResultSet(ResultSet resultSet)
            throws SQLException {
        // creating Lesson
        String id = resultSet.getString("lesson_id");
        String teacherID = resultSet.getString("user_id");
        String full_name = resultSet.getString("full_name");
        Teacher teacher = new Teacher(full_name, teacherID);

        String subject = resultSet.getString("subject");
        String topic = resultSet.getString("topic");
        LocalDate date = resultSet.getDate("lesson_date").toLocalDate();
        LocalTime timeFrom = resultSet.getTime("time_from").toLocalTime();
        LocalTime timeTo = resultSet.getTime("time_to").toLocalTime();
        String contents = resultSet.getString("description");
        String classroom = resultSet.getString("classroom");
        String homework = resultSet.getString("homework");
        String className = resultSet.getString("class_name");
        Lesson lesson = new Lesson(
                id,
                teacher,
                new Date(date),
                new Time(timeFrom),
                new Time(timeTo),
                subject,
                topic,
                contents,
                classroom,
                homework,
                className
        );

        // creating Student
        String userID = resultSet.getString("user_id");
        String name = resultSet.getString("full_name");
        Student student = new Student(name, userID);
        student.setClassName(className);

        // creating LessonData
        LessonData lessonData = new LessonData(lesson, student);

        Absence absence = new Absence(resultSet.getBoolean("absence_status"), resultSet.getString("absence_motive"));
        lessonData.setAbsence(absence);

        Grade grade = new Grade(resultSet.getInt("grade"), resultSet.getString("comment"));
        lessonData.setGrade(grade);

        return lessonData;

    }

    @Override
    public void updateGradeComment(LessonData lessonData) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE lesson_data SET grade = ?, comment = ? WHERE user_id = ? and lesson_id = ?");
            statement.setInt(1, lessonData.getGrade().getGrade());
            statement.setString(2, lessonData.getGrade().getComment());
            statement.setString(3, lessonData.getStudent().getID());
            statement.setInt(4, Integer.parseInt(lessonData.getLesson().getId()));
            statement.executeUpdate();
        }
    }

//    @Override
//    public void updateComment(LessonData lessonData)
//            throws SQLException  //Update statement
//    {
//        try (Connection connection = getConnection()) {
//            PreparedStatement statement = connection.prepareStatement(
//                    "UPDATE lesson_data SET comment = ? WHERE user_id = ? and lesson_id = ?");
//            statement.setString(1, lessonData.getGrade().getComment());
//            statement.setString(2, lessonData.getStudent().getID());
//            statement.setString(3, lessonData.getLesson().getId());
//            statement.executeUpdate();
//        }
//    }

    @Override
    public void updateAbsenceStatus(LessonData lessonData)   //Update statement for changing status to opposite
            throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE lesson_data SET absence_status = not absence_status WHERE user_id = ? and lesson_id = ?");
            statement.setString(1, lessonData.getStudent().getID());
            statement.setInt(2, Integer.parseInt(lessonData.getLesson().getId()));
            statement.executeUpdate();
        }
    }

    @Override
    public void updateAbsenceMotive(LessonData lessonData)   //Update statement for changing motive
            throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE lesson_data SET absence_motive = ? WHERE user_id = ? and lesson_id = ?");
            statement.setString(1, lessonData.getAbsence().getMotive());
            statement.setString(2, lessonData.getStudent().getID());
            statement.setString(3, lessonData.getLesson().getId());
            statement.executeUpdate();
        }
    }
}
