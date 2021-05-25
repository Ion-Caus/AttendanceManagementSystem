package mediator;

import model.*;
import model.Class;
import model.packages.Package;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.PropertyChangeHandler;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class RemoteModelManager implements RemoteModel, LocalListener<String, Package> {
    private PropertyChangeHandler<String, Package> property;
    private Model model;

    public RemoteModelManager(Model model) throws RemoteException, MalformedURLException {
        this.model = model;
        this.property = new PropertyChangeHandler<>(this);

        startRegistry();
        startServer();
        model.addListener(this);
    }

    private void startRegistry() throws RemoteException {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            System.out.println("Registry started...");
        } catch (java.rmi.server.ExportException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void startServer() throws RemoteException, MalformedURLException {
        UnicastRemoteObject.exportObject(this, 0);
        Naming.rebind("AMS", this);
    }

    public void close() {
        try {
            UnicastRemoteObject.unexportObject(this, true);
        } catch (NoSuchObjectException e) {
            // do nothing
        }
    }

    @Override
    public void propertyChange(ObserverEvent<String, Package> event) {

    }

    @Override
    public boolean addListener(GeneralListener<String, Package> listener, String... propertyNames) throws RemoteException {
        return property.addListener(listener, propertyNames);
    }

    @Override
    public boolean removeListener(GeneralListener<String, Package> listener, String... propertyNames) throws RemoteException {
        return property.removeListener(listener, propertyNames);
    }

    @Override
    public String getClassAndSchool(Student student) {
        return model.getClassAndSchool(student);
    }

    @Override
    public void setSchoolName(String name) {
        model.setSchoolName(name);
    }

    @Override
    public String getSchoolName() {
        return model.getSchoolName();
    }

    @Override
    public ArrayList<Class> getAllClasses() {
        return model.getAllClasses();
    }

    @Override
    public ArrayList<Student> getAllStudents() {
        return model.getAllStudents();
    }

    @Override
    public ArrayList<String> getUnassignedStudents() {
        return model.getUnassignedStudents();
    }

    @Override
    public ArrayList<Student> getStudentsByClass(String className) {
        return model.getStudentsByClass(className);
    }

    @Override
    public ArrayList<Teacher> getAllTeachers() {
        return model.getAllTeachers();
    }

    @Override
    public ArrayList<Lesson> getScheduleFor(Class theClass, LocalDate date) {
        return model.getScheduleFor(theClass,date);
    }

    @Override
    public ArrayList<Lesson> getScheduleFor(Student student, LocalDate date) {
        return model.getScheduleFor(student, date);
    }

    @Override
    public ArrayList<Lesson> getScheduleFor(Teacher teacher, LocalDate date) {
        return model.getScheduleFor(teacher, date);
    }

    @Override
    public Class getClassWith(Student student) {
        return model.getClassWith(student);
    }

    @Override
    public Class getClassByName(String name) {
        return model.getClassByName(name);
    }

    @Override
    public Student getStudentBy(String id) throws IllegalArgumentException {
        return model.getStudentBy(id);
    }

    @Override
    public Teacher getTeacherBy(String id) throws IllegalArgumentException {
        return model.getTeacherBy(id);
    }

    @Override
    public Lesson getLesson(String lessonID, Student student) throws IllegalArgumentException {
        return model.getLesson(lessonID, student);
    }

    @Override
    public Lesson getLesson(String lessonID) throws IllegalArgumentException {
        return model.getLesson(lessonID);
    }

    @Override
    public Lesson getLesson(String lessonID, Class aClass) throws IllegalArgumentException {
        return model.getLesson(lessonID, aClass);
    }

    @Override
    public LessonData getLessonData(Lesson lesson, Student student) {
        return model.getLessonData(lesson, student);
    }

    @Override
    public boolean changeMotive(String studentId, String lessonID, String motive) throws SQLException {
        return model.changeMotive(studentId, lessonID, motive);
    }

    @Override
    public boolean changeAbsence(String studentID, String lessonID, boolean absence) throws SQLException {
        return model.changeAbsence(studentID, lessonID, absence);
    }

    @Override
    public boolean changeLesson(String lessonID, String topic, String contents, String homework, String teacherID) throws SQLException {
        return model.changeLesson(lessonID, topic, contents, homework, teacherID);
    }

    @Override
    public void addClass(String className) throws IllegalArgumentException, SQLException {
        model.addClass(className);
    }

    @Override
    public void removeClass(String className) throws IllegalAccessException, SQLException {
        model.removeClass(className);
    }

    @Override
    public void addStudent(String studentName, String studentID) throws IllegalArgumentException, SQLException {
        model.addStudent(studentName,studentID);
    }

    @Override
    public void removeStudent(String studentID) throws SQLException {
        model.removeStudent(studentID);
    }

    @Override
    public void addStudentToClass(String studentID, String className) throws IllegalArgumentException, SQLException {
        model.addStudentToClass(studentID, className);
    }

    @Override
    public void removeStudentFromClass(String studentID, String className) throws IllegalArgumentException, SQLException {
        model.removeStudentFromClass(studentID, className);
    }

    @Override
    public void addTeacher(String teacherName, String teacherID) throws IllegalArgumentException, SQLException {
        model.addTeacher(teacherName, teacherID);
    }

    @Override
    public void removeTeacher(String studentID) throws SQLException {
        model.removeTeacher(studentID);
    }

    @Override
    public void addLesson(Class aClass, Lesson lesson) throws SQLException {
        model.addLesson(aClass, lesson);
    }

    @Override
    public void removeLesson(String className, String lessonID) throws SQLException {
        model.removeLesson(className, lessonID);
    }

    @Override
    public void changeGradeComment(String studentID, String lessonID, int grade, String comment) throws SQLException {
        model.changeGradeComment(studentID, lessonID, grade, comment);
    }
}