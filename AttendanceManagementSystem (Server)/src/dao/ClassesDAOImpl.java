package dao;

import model.Class;

import java.sql.*;
import java.util.ArrayList;

public class ClassesDAOImpl implements ClassesDAO {
    private static ClassesDAOImpl instance;

    private ClassesDAOImpl() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public static synchronized ClassesDAOImpl getInstance() throws SQLException {
        if (instance == null) {
            instance = new ClassesDAOImpl();
        }
        return instance;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres?currentSchema=attendance_management_system",
                "postgres", "admin");
        //TODO change password to your local database password for it to work
    }

    @Override
    public void addClass(Class aClass) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO classes(class_name) VALUES (?);");
            statement.setString(1, aClass.getClassName());
            statement.executeUpdate();
        }
    }

    private static Class createClass(ResultSet resultSet) throws SQLException {
        String className = resultSet.getString("class_name");
        return new Class(className);
    }

    @Override
    public ArrayList<Class> readClasses() throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT distinct class_name FROM classes");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Class> result = new ArrayList<>();
            while (resultSet.next()) {
                Class aClass = createClass(resultSet);
                result.add(aClass);
            }
            return result;
        }
    }

    @Override
    public void removeClass(String className) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("DELETE FROM classes WHERE class_name = ?");
            statement.setString(1, className);
            statement.executeUpdate();
        }
    }

}