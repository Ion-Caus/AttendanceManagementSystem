package mediator;

import model.*;
import model.Class;
import model.packages.Package;
import utility.observer.subject.RemoteSubject;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface RemoteModel extends RemoteSubject<String, Package> {
    String login(String userID, String password) throws IllegalAccessException, RemoteException, SQLException;

    ArrayList<Class> getAllClasses() throws RemoteException;

    ArrayList<Student> getAllStudents() throws RemoteException;
    ArrayList<String> getUnassignedStudents() throws RemoteException;

    ArrayList<Student> getStudentsByClass(String className) throws RemoteException;

    ArrayList<Teacher> getAllTeachers() throws RemoteException;

    ArrayList<Lesson> getScheduleFor(Class theClass, LocalDate date) throws RemoteException;
    ArrayList<Lesson> getScheduleFor(Student student, LocalDate date) throws RemoteException;
    ArrayList<Lesson> getScheduleFor(Teacher teacher, LocalDate date) throws RemoteException;

    Class getClassWith(Student student) throws RemoteException;
    Class getClassByName(String name) throws RemoteException;

    Student getStudentBy(String id) throws IllegalArgumentException, RemoteException;

    Teacher getTeacherBy(String id) throws IllegalArgumentException, RemoteException;

    Lesson getLesson(String lessonID, Student student) throws IllegalArgumentException, RemoteException;
    Lesson getLesson(String lessonID) throws IllegalArgumentException, RemoteException;
    Lesson getLesson(String lessonID, Class aClass) throws IllegalArgumentException, RemoteException;

    LessonData getLessonData(Lesson lesson, Student student) throws RemoteException;

    boolean changeMotive(String studentId, String lessonID, String motive) throws SQLException, RemoteException;
    boolean changeAbsence(String studentID, String lessonID, boolean absence) throws SQLException, RemoteException;
    boolean changeLesson(String lessonID, String topic, String contents, String homework, String teacherID) throws SQLException, RemoteException;

    void addClass(String className) throws IllegalArgumentException, SQLException, RemoteException;
    void removeClass(String className) throws IllegalAccessException, SQLException, RemoteException;

    void addStudent(String studentName, String studentID) throws IllegalArgumentException, SQLException, RemoteException;
    void removeStudent(String studentID) throws SQLException, RemoteException;

    void addStudentToClass(String studentID, String className) throws IllegalArgumentException, SQLException, RemoteException;
    void removeStudentFromClass(String studentID, String className) throws IllegalArgumentException, SQLException, RemoteException;

    void addTeacher(String teacherName, String teacherID) throws IllegalArgumentException, SQLException, RemoteException;
    void removeTeacher(String studentID) throws SQLException, RemoteException;
    void addLesson(Class aClass, Lesson lesson) throws SQLException, RemoteException;
    void removeLesson(String className, String lessonID) throws SQLException, RemoteException;

    void changeGradeComment(String studentID, String lessonID, int grade, String comment) throws SQLException, RemoteException;

    void changePassword(String id, String password) throws IllegalArgumentException, SQLException, RemoteException;
}
