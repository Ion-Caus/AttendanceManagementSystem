package model;

import utility.observer.subject.LocalSubject;

import java.time.LocalDate;
import java.util.ArrayList;

public interface Model extends LocalSubject<String,String> {

    String getClassAndSchool(Student student);

    void setSchoolName(String name);
    String getSchoolName();

    ArrayList<Class> getAllClasses();

    ArrayList<Student> getAllStudents();

    ArrayList<Teacher> getAllTeachers();

    ArrayList<Lesson> getScheduleFor(Class theClass, LocalDate date);

    ArrayList<Lesson> getScheduleFor(Student student, LocalDate date);

    Class getClassWith(Student student);

    Class getClassByName(String name);

    Student getStudentBy(String id) throws IllegalArgumentException;

    void addClass(String className);
    void removeClass(String className);

    void addStudent(String studentID, String studentName);
    void removeStudent(String studentID);
}
