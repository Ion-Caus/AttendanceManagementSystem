package model;

import model.packages.Package;
import utility.observer.subject.LocalSubject;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface Model extends LocalSubject<String, Package> {
    String login(String userID, String password) throws IllegalAccessException, SQLException;

    ArrayList<StudentClass> getAllClasses();

    ArrayList<Student> getAllStudents();
    ArrayList<String> getUnassignedStudents();

    ArrayList<Student> getStudentsByClass(String className);

    ArrayList<Teacher> getAllTeachers();

    ArrayList<Lesson> getScheduleFor(StudentClass theClass, LocalDate date);
    ArrayList<Lesson> getScheduleFor(Student student, LocalDate date);
    ArrayList<Lesson> getScheduleFor(Teacher teacher, LocalDate date);

    StudentClass getClassWith(Student student);
    StudentClass getClassByName(String name);

    Student getStudentBy(String id) throws IllegalArgumentException;

    Teacher getTeacherBy(String id) throws IllegalArgumentException;

    Lesson getLesson(String lessonID, Student student) throws IllegalArgumentException;
    Lesson getLesson(String lessonID) throws IllegalArgumentException;
    Lesson getLesson(String lessonID, StudentClass aClass) throws IllegalArgumentException;

    LessonData getLessonData(Lesson lesson, Student student);

    boolean changeMotive(String studentId, String lessonID, String motive) throws SQLException;
    boolean changeAbsence(String studentID, String lessonID, boolean absence) throws SQLException;
    boolean changeLesson(String lessonID, String topic, String contents, String homework, String teacherID) throws SQLException;

    void addClass(String className) throws IllegalArgumentException, SQLException;
    void removeClass(String className) throws IllegalAccessException, SQLException;

    void addStudent(String studentName, String studentID) throws IllegalArgumentException, SQLException;
    void removeStudent(String studentID) throws SQLException;

    void addStudentToClass(String studentID, String className) throws IllegalArgumentException, SQLException;
    void removeStudentFromClass(String studentID, String className) throws IllegalArgumentException, SQLException;

    void addTeacher(String teacherName, String teacherID) throws IllegalArgumentException, SQLException;
    void removeTeacher(String studentID) throws SQLException;
    void addLesson(String className, Lesson lesson) throws SQLException;
    void removeLesson(String className, String lessonID) throws SQLException, IllegalArgumentException;

    void changeGradeComment(String studentID, String lessonID, int grade, String comment) throws SQLException;

    void changePassword(String id, String password) throws IllegalArgumentException, SQLException;

}
