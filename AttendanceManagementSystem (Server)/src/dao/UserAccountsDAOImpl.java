package dao;

import model.Account;
import model.Administrator;
import model.Student;
import model.Teacher;

import java.sql.*;
import java.util.ArrayList;

public class UserAccountsDAOImpl implements UserAccountsDAO {
    private static UserAccountsDAOImpl instance;
    public static final String STUDENT = "student", TEACHER = "teacher", ADMIN = "admin";

    private UserAccountsDAOImpl() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
        createDefaultIfNotExists();
    }

    public static synchronized UserAccountsDAOImpl getInstance()
            throws SQLException {
        if (instance == null) {
            instance = new UserAccountsDAOImpl();
        }
        return instance;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres?currentSchema=attendance_management_system",
                "postgres", "admin");
    }

    private void createDefaultIfNotExists() throws SQLException {
        try (Connection connection = getConnection()) {
            connection
                    .prepareStatement(
                            "INSERT INTO user_account(user_id, full_name, password, access)" +
                            "VALUES ('000000', 'Not / Assigned', 'null', 'teacher') ON CONFLICT DO NOTHING;")
                    .executeUpdate();
        }
    }

    @Override
    public void createUserAccount(String name, String ID,
                                  String password, String access) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO user_account(user_id, full_name, password, access) VALUES (?, ?, ?, ?);");
            statement.setString(1, ID);
            statement.setString(2, name);
            statement.setString(3, password);
            statement.setString(4, access);
            statement.executeUpdate();
        }
    }

    @Override
    public ArrayList<Student> readAllStudents() throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT user_id, full_name, class_name from student_list full join User_account using (user_id) where access='student';");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Student> result = new ArrayList<>();
            while (resultSet.next()) {
                Student student = createStudent(resultSet);
                result.add(student);
            }
            return result;
        }
    }


    private static Student createStudent(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("full_name");
        String studentID = resultSet.getString("user_id");
        Student student = new Student(name, studentID);
        student.setClassName(resultSet.getString("class_name"));
        return student;
    }

    @Override
    public ArrayList<Teacher> readTeachers() throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM user_account WHERE access = 'teacher';");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Teacher> result = new ArrayList<>();
            while (resultSet.next()) {
                Teacher teacher = createTeacher(resultSet);
                result.add(teacher);
            }
            return result;
        }
    }

    public static Teacher createTeacher(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("full_name");
        String studentID = resultSet.getString("user_id");
        return new Teacher(name, studentID);
    }

    @Override
    public ArrayList<Administrator> readAdmins() throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM user_account WHERE access = 'admin';");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Administrator> result = new ArrayList<>();
            while (resultSet.next()) {
                Administrator admin = createAdmin(resultSet);
                result.add(admin);
            }
            return result;
        }
    }

    public static Administrator createAdmin(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("full_name");
        String studentID = resultSet.getString("user_id");
        return new Administrator(name, studentID);
    }

    @Override
    public void updateUserAccount(Account account) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("UPDATE user_account SET password = ? WHERE user_id = ?");
            statement.setString(1, account.getPassword().toString());
            statement.setString(2, account.getUsername().toString());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteUser(String userID) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement deleteUA = connection
                    .prepareStatement("DELETE FROM user_account WHERE user_id = ?;");
            deleteUA.setString(1, userID);
            deleteUA.executeUpdate();
        }
    }

    @Override
    public void deleteTeacher(String teacherID) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement deleteTaught = connection
                    .prepareStatement("UPDATE taught_by set teacher_id=? WHERE teacher_id = ?;");
            deleteTaught.setString(1, "000000");
            deleteTaught.setString(2, teacherID);
            deleteTaught.executeUpdate();
            deleteUser(teacherID);
        }
    }

    @Override
    public String login(String username, String password) throws SQLException, IllegalAccessException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT user_id, password, access from user_account where user_id = ? and password = ?;");

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("access");
            }
            throw new IllegalAccessException("Wrong username or password.");
        }
    }
}