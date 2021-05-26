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
        try {
            return remoteModel.getUnassignedStudents();
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    @Override
    public ArrayList<Student> getStudentsByClass(String className) {
        try {
            return remoteModel.getStudentsByClass(className);
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    @Override
    public ArrayList<Teacher> getAllTeachers() {
        try {
            return remoteModel.getAllTeachers();
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    @Override
    public ArrayList<Lesson> getScheduleFor(Class theClass, LocalDate date) {
        try {
            return remoteModel.getScheduleFor(theClass, date);
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    @Override
    public ArrayList<Lesson> getScheduleFor(Student student, LocalDate date) {
        try {
            return remoteModel.getScheduleFor(student, date);
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    @Override
    public ArrayList<Lesson> getScheduleFor(Teacher teacher, LocalDate date) {
        try {
            return remoteModel.getScheduleFor(teacher, date);
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    @Override
    public Class getClassWith(Student student) {
        try {
            return remoteModel.getClassWith(student);
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    @Override
    public Class getClassByName(String name) {
        try {
            return remoteModel.getClassByName(name);
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    @Override
    public Student getStudentBy(String id) throws IllegalArgumentException {
        try {
            return remoteModel.getStudentBy(id);
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    @Override
    public Teacher getTeacherBy(String id) throws IllegalArgumentException {
        try {
            return remoteModel.getTeacherBy(id);
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    @Override
    public Lesson getLesson(String lessonID, Student student) throws IllegalArgumentException {
        try {
            return remoteModel.getLesson(lessonID, student);
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    @Override
    public Lesson getLesson(String lessonID) throws IllegalArgumentException {
        try {
            return remoteModel.getLesson(lessonID);
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    @Override
    public Lesson getLesson(String lessonID, Class aClass) throws IllegalArgumentException {
        try {
            return remoteModel.getLesson(lessonID, aClass);
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    @Override
    public LessonData getLessonData(Lesson lesson, Student student) {
        try {
            return remoteModel.getLessonData(lesson, student);
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    @Override
    public boolean changeMotive(String studentId, String lessonID, String motive) throws SQLException {
        try {
            return remoteModel.changeMotive(studentId, lessonID, motive);
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    @Override
    public boolean changeAbsence(String studentID, String lessonID, boolean absence) throws SQLException {
        try {
            return remoteModel.changeAbsence(studentID, lessonID, absence);
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    @Override
    public boolean changeLesson(String lessonID, String topic, String contents, String homework, String teacherID) throws SQLException {
        try {
            return remoteModel.changeLesson(lessonID, topic, contents, homework, teacherID);
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    @Override
    public void addClass(String className) throws IllegalArgumentException, SQLException {
        try {
            remoteModel.addClass(className);
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    @Override
    public void removeClass(String className) throws IllegalAccessException, SQLException {
        try {
            remoteModel.removeClass(className);
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    @Override
    public void addStudent(String studentName, String studentID) throws IllegalArgumentException, SQLException {
        try {
            remoteModel.addStudent(studentName, studentID);
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    @Override
    public void removeStudent(String studentID) throws SQLException {
        try {
            remoteModel.removeStudent(studentID);
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    @Override
    public void addStudentToClass(String studentID, String className) throws IllegalArgumentException, SQLException {
        try {
            remoteModel.addStudentToClass(studentID, className);
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    @Override
    public void removeStudentFromClass(String studentID, String className) throws IllegalArgumentException, SQLException {
        try {
            remoteModel.removeStudentFromClass(studentID, className);
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    @Override
    public void addTeacher(String teacherName, String teacherID) throws IllegalArgumentException, SQLException {
        try {
            remoteModel.addTeacher(teacherName, teacherID);
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    @Override
    public void removeTeacher(String studentID) throws SQLException {
        try {
            remoteModel.removeTeacher(studentID);
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    @Override
    public void addLesson(Class aClass, Lesson lesson) throws SQLException {
        try {
            remoteModel.addLesson(aClass, lesson);
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    @Override
    public void removeLesson(String className, String lessonID) throws SQLException {
        try {
            remoteModel.removeLesson(className, lessonID);
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    @Override
    public void changeGradeComment(String studentID, String lessonID, int grade, String comment) throws SQLException {
        try {
            remoteModel.changeGradeComment(studentID, lessonID, grade, comment);
        } catch (RemoteException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
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
    public synchronized void propertyChange(ObserverEvent<String, Package> event) throws RemoteException {
        Platform.runLater(() ->
                {
                    property.firePropertyChange(event.getPropertyName(), event.getValue1(), event.getValue2());
                    System.out.println(event.getPropertyName() + " " + event.getValue2().getID());
                }
        );
    }
}
