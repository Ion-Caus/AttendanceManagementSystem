package model;

import utility.observer.subject.LocalSubject;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface Model extends LocalSubject<String,String> {

    String getClassAndSchool(Student student);

    void setSchoolName(String name);
    String getSchoolName();

    ArrayList<Class> getAllClasses() throws SQLException;

    ArrayList<Student> getAllStudents();
    ArrayList<String> getUnassignedStudents();

    ArrayList<Teacher> getAllTeachers();

    ArrayList<Lesson> getScheduleFor(Class theClass, LocalDate date);

    ArrayList<Lesson> getScheduleFor(Student student, LocalDate date);

    Class getClassWith(Student student);

    Class getClassByName(String name);

    Student getStudentBy(String id) throws IllegalArgumentException;

    void addClass(String className)
        throws IllegalArgumentException, SQLException;
    void removeClass(String className)
        throws IllegalAccessException, SQLException;

    void addStudent(String studentName, String studentID) throws IllegalArgumentException;
    void removeStudent(String studentID);

    void addStudentToClass(String studentID, String className) throws IllegalArgumentException;
    void removeStudentFromClass(String studentID, String className) throws IllegalArgumentException;

    void addTeacher(String teacherName, String teacherID) throws IllegalArgumentException;
    void removeTeacher(String studentID);
}
