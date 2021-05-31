package model;

import javafx.application.Platform;
import mediator.AttendanceManagementClient;
import mediator.ClientModel;
import model.packages.Package;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.PropertyChangeHandler;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ModelManager implements Model, LocalListener<String, Package> {
    private PropertyChangeHandler<String, Package> property;
    private ClientModel clientModel;

    public ModelManager() throws MalformedURLException, NotBoundException, RemoteException {
        property = new PropertyChangeHandler<>(this);
        clientModel = new AttendanceManagementClient();
        clientModel.addListener(this);
    }

    @Override
    public String login(String userID, String password) throws IllegalAccessException {
        return clientModel.login(userID, password);
    }

    @Override
    public ArrayList<Class> getAllClasses() {
        return clientModel.getAllClasses();
    }

    @Override
    public ArrayList<Student> getAllStudents() {
        return clientModel.getAllStudents();
    }

    @Override
    public ArrayList<String> getUnassignedStudents() {
        return clientModel.getUnassignedStudents();
    }

    @Override
    public ArrayList<Student> getStudentsByClass(String className) {
        return clientModel.getStudentsByClass(className);
    }

    @Override
    public ArrayList<Teacher> getAllTeachers() {
        return clientModel.getAllTeachers();
    }

    @Override
    public ArrayList<Lesson> getScheduleFor(Class theClass, LocalDate date) {
        return clientModel.getScheduleFor(theClass, date);
    }

    @Override
    public ArrayList<Lesson> getScheduleFor(Student student, LocalDate date) {
        return clientModel.getScheduleFor(student, date);
    }

    @Override
    public ArrayList<Lesson> getScheduleFor(Teacher teacher, LocalDate date) {
        return clientModel.getScheduleFor(teacher, date);
    }

    @Override
    public Class getClassWith(Student student) {
        return clientModel.getClassWith(student);
    }

    @Override
    public Class getClassByName(String name) {
        return clientModel.getClassByName(name);
    }

    @Override
    public Student getStudentBy(String id) throws IllegalArgumentException {
        return clientModel.getStudentBy(id);
    }

    @Override
    public Teacher getTeacherBy(String id) throws IllegalArgumentException {
        return clientModel.getTeacherBy(id);
    }

    @Override
    public Lesson getLesson(String lessonID, Student student) throws IllegalArgumentException {
        return clientModel.getLesson(lessonID, student);
    }

    @Override
    public Lesson getLesson(String lessonID) throws IllegalArgumentException {
        return clientModel.getLesson(lessonID);
    }

    @Override
    public Lesson getLesson(String lessonID, Class aClass) throws IllegalArgumentException {
        return clientModel.getLesson(lessonID, aClass);
    }

    @Override
    public LessonData getLessonData(Lesson lesson, Student student) {
        return clientModel.getLessonData(lesson, student);
    }

    @Override
    public boolean changeMotive(String studentId, String lessonID, String motive) throws SQLException {
        return clientModel.changeMotive(studentId, lessonID, motive);
    }

    @Override
    public boolean changeAbsence(String studentID, String lessonID, boolean absence) throws SQLException {
        return clientModel.changeAbsence(studentID, lessonID, absence);
    }

    @Override
    public boolean changeLesson(String lessonID, String topic, String contents, String homework, String teacherID) throws SQLException {
        return clientModel.changeLesson(lessonID, topic, contents, homework, teacherID);
    }

    @Override
    public void addClass(String className) throws IllegalArgumentException, SQLException {
        clientModel.addClass(className);
    }

    @Override
    public void removeClass(String className) throws IllegalAccessException, SQLException {
        clientModel.removeClass(className);
    }

    @Override
    public void addStudent(String studentName, String studentID) throws IllegalArgumentException, SQLException {
        clientModel.addStudent(studentName, studentID);
    }

    @Override
    public void removeStudent(String studentID) throws SQLException {
        clientModel.removeStudent(studentID);
    }

    @Override
    public void addStudentToClass(String studentID, String className) throws IllegalArgumentException, SQLException {
        clientModel.addStudentToClass(studentID, className);
    }

    @Override
    public void removeStudentFromClass(String studentID, String className) throws IllegalArgumentException, SQLException {
        clientModel.removeStudentFromClass(studentID, className);
    }

    @Override
    public void addTeacher(String teacherName, String teacherID) throws IllegalArgumentException, SQLException {
        clientModel.addTeacher(teacherName, teacherID);
    }

    @Override
    public void removeTeacher(String studentID) throws SQLException {
        clientModel.removeTeacher(studentID);
    }

    @Override
    public void addLesson(String className, Lesson lesson) throws SQLException {
        clientModel.addLesson(className, lesson);
    }

    @Override
    public void removeLesson(String className, String lessonID) throws SQLException {
        clientModel.removeLesson(className, lessonID);
    }

    @Override
    public void changeGradeComment(String studentID, String lessonID, int grade, String comment) throws SQLException {
        clientModel.changeGradeComment(studentID, lessonID, grade, comment);
    }

    @Override
    public void changePassword(String id, String password) throws IllegalArgumentException, SQLException {
        clientModel.changePassword(id,password);
    }

    @Override
    public void close() {
        clientModel.close();
    }

    @Override
    public boolean addListener(GeneralListener<String, Package> listener, String... propertyNames) {
        return property.addListener(listener, propertyNames);
    }

    @Override
    public boolean removeListener(GeneralListener<String, Package> listener, String... propertyNames) {
        return property.removeListener(listener, propertyNames);
    }

    @Override
    public void propertyChange(ObserverEvent<String, Package> event) {
        Platform.runLater( () ->
                property.firePropertyChange(event.getPropertyName(), event.getValue1(), event.getValue2())
        );
    }
}
