package model;

import java.time.LocalDate;
import java.util.ArrayList;

public interface Model {

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

    Student getStudentBy(String id);
}
