package mediator;

import javafx.application.Platform;
import model.*;
import model.Class;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.RemoteListener;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;

import java.rmi.*;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import model.packages.Package;

public class AttendanceManagementClient implements ClientModel, RemoteListener<String, Package>{
    private RemoteModel remoteModel;
    private PropertyChangeHandler<String, Package> property;

    public static final String HOST = "localhost";

    public AttendanceManagementClient() throws RemoteException, NotBoundException, MalformedURLException {
        this.remoteModel = (RemoteModel) Naming.lookup("rmi://" + HOST + ":1099/AMS");
        UnicastRemoteObject.exportObject(this, 0);
        remoteModel.addListener(this);
        this.property = new PropertyChangeHandler<>(this);
    }

    @Override
    public String getClassAndSchool(Student student) {
        try {
            return remoteModel.getClassAndSchool(student);
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    @Override
    public void setSchoolName(String name) {
        try {
            remoteModel.setSchoolName(name);
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    @Override
    public String getSchoolName() {
        try {
            return remoteModel.getSchoolName();
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    @Override
    public ArrayList<Class> getAllClasses() {
        try {
            return remoteModel.getAllClasses();
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    @Override
    public ArrayList<Student> getAllStudents() {
        try {
            return remoteModel.getAllStudents();
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    @Override
    public ArrayList<String> getUnassignedStudents() {
        return null;
    }

    @Override
    public ArrayList<Student> getStudentsByClass(String className) {
        return null;
    }

    @Override
    public ArrayList<Teacher> getAllTeachers() {
        return null;
    }

    @Override
    public ArrayList<Lesson> getScheduleFor(Class theClass, LocalDate date) {
        return null;
    }

    @Override
    public ArrayList<Lesson> getScheduleFor(Student student, LocalDate date) {
        return null;
    }

    @Override
    public ArrayList<Lesson> getScheduleFor(Teacher teacher, LocalDate date) {
        return null;
    }

    @Override
    public Class getClassWith(Student student) {
        return null;
    }

    @Override
    public Class getClassByName(String name) {
        return null;
    }

    @Override
    public Student getStudentBy(String id) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Teacher getTeacherBy(String id) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Lesson getLesson(String lessonID, Student student) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Lesson getLesson(String lessonID) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Lesson getLesson(String lessonID, Class aClass) throws IllegalArgumentException {
        return null;
    }

    @Override
    public LessonData getLessonData(Lesson lesson, Student student) {
        return null;
    }

    @Override
    public boolean changeMotive(String studentId, String lessonID, String motive) throws SQLException {
        return false;
    }

    @Override
    public boolean changeAbsence(String studentID, String lessonID, boolean absence) throws SQLException {
        return false;
    }

    @Override
    public boolean changeLesson(String lessonID, String topic, String contents, String homework, String teacherID) throws SQLException {
        return false;
    }

    @Override
    public void addClass(String className) throws IllegalArgumentException, SQLException {

    }

    @Override
    public void removeClass(String className) throws IllegalAccessException, SQLException {

    }

    @Override
    public void addStudent(String studentName, String studentID) throws IllegalArgumentException, SQLException {

    }

    @Override
    public void removeStudent(String studentID) throws SQLException {

    }

    @Override
    public void addStudentToClass(String studentID, String className) throws IllegalArgumentException, SQLException {

    }

    @Override
    public void removeStudentFromClass(String studentID, String className) throws IllegalArgumentException, SQLException {

    }

    @Override
    public void addTeacher(String teacherName, String teacherID) throws IllegalArgumentException, SQLException {

    }

    @Override
    public void removeTeacher(String studentID) throws SQLException {

    }

    @Override
    public void addLesson(Class aClass, Lesson lesson) throws SQLException {

    }

    @Override
    public void removeLesson(String className, String lessonID) throws SQLException {

    }

    @Override
    public void changeGradeComment(String studentID, String lessonID, int grade, String comment) throws SQLException {

    }

    @Override
    public void close() {
        try {
            UnicastRemoteObject.unexportObject(this, true);
        }
        catch (NoSuchObjectException e) {
            // do nothing
        }
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
    public void propertyChange(ObserverEvent<String, Package> event) throws RemoteException {
        Platform.runLater( () ->
                property.firePropertyChange(event.getPropertyName(), event.getValue1(), event.getValue2())
        );
    }

}
