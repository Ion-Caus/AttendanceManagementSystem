package dao;

import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentListDAOImpl implements StudentListDAO {
    private static StudentListDAOImpl instance;

    private StudentListDAOImpl() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public static synchronized StudentListDAOImpl getInstance()
            throws SQLException {
        if (instance == null) {
            instance = new StudentListDAOImpl();
        }
        return instance;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres?currentSchema=attendance_management_system",
                "postgres", "admin");
    }

    private static Student createStudent(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("full_name");
        String id = resultSet.getString("user_id");
        return new Student(name, id);
    }


    @Override
    public List<Student> readClass(String className) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT full_name, user_id FROM user_account join student_list using (user_id) where class_name=?");
            statement.setString(1, className);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Student> result = new ArrayList<>();
            while (resultSet.next()) {
                Student student = createStudent(resultSet);
                result.add(student);
            }
            return result;
        }
    }

    @Override
    public void addToClass(String className, String userID)
            throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO student_list(class_name, user_id) VALUES (?, ?);");
            statement.setString(1, className);
            statement.setString(2, userID);
            statement.executeUpdate();
        }
    }

    @Override
    public void removeFromClass(String className, String userID)
            throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM student_list WHERE class_name = ? and user_id= ?");
            statement.setString(1, className);
            statement.setString(2, userID);
            statement.executeUpdate();
        }
    }
}
