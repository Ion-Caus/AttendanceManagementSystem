package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;


/**
 * This class functions as a containing element for class Student and it represents the list of students that are in one class.
 */
public class StudentList implements Serializable {
    private ArrayList<Student> students;

    /**
     * 0 argument constructor that will initialize students ArrayList to new ArrayList
     */
    public StudentList() {
        this.students = new ArrayList<>();
    }

    /** 1 argument constructor that will initialize students Arraylist using the ArrayList provided as argument
     * @param students
     */
    public StudentList(ArrayList<Student> students) {
        this.students = students;
    }

    /**The purpose of this method is to add new student to the students ArrayList
     * @param student the student that is supposed to be added
     * @throws IllegalArgumentException will be thrown in case either if the student is already in another class or in case a student with that ID already exists
     */
    public void addStudent(Student student) throws IllegalArgumentException {
        if (!isUnique(student.getID()))
            throw new IllegalArgumentException("Student with ID ("+ student.getID() + ") already exists.");

        if(!(student.getClassName()==null))
            throw new IllegalArgumentException("This student is already in another class.");

        students.add(student);
    }


    /**This method will remove student from the list based on the student object provided as an argument
     * @param student the student object provided as an argument
     */
    public void removeStudent(Student student) {
        students.remove(student);
    }

    /**This method will remove student from the list based on the student id provided as an argument
     * @param studentID the studentID provided as an argument
     */
    public void removeStudent(String studentID) {
        students.removeIf(student -> student.getID().equals(studentID));
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }

    /**
     * @return ArrayList of students that are not yet assigned to any class
     */
    public ArrayList<String> getUnassignedStudents() {
        ArrayList<String> unassignedStudents = new ArrayList<>();
        for (Student student: students) {
            if (student.getClassName() == null) {
                unassignedStudents.add(String.format("%s (%S)",student.getName(), student.getID()));
            }
        }
        return unassignedStudents;
    }

    /**
     * @param id the id provided as an argument
     * @return the student object that has matching id with the id provided as an argument
     * @throws IllegalArgumentException will be thrown if no student is found using the id provided
     */
    public Student getStudentByID(String id) throws IllegalArgumentException {
        for (Student student: students) {
            if (student.getID().equals(id)) {
                return student;
            }
        }
        throw new IllegalArgumentException("No such student with this id (" + id + ")");
    }

    /**
     * @param studentID student id provided as an argument
     * @return true if there is no student with this id, false if there is a student matching the provided id
     */
    private boolean isUnique(String studentID) {
        return students.stream().noneMatch(student -> student.getID().equals(studentID));
    }

    // TODO: 31/5/2021 tomas no usage
    public Student getStudentByName(String name) throws IllegalArgumentException {
        for (Student student: students) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        throw new IllegalArgumentException("No such student with this name (" + name + ")");
    }

    /**
     * This method will get all students that are in the class provided as an argument
     * @param className The className provided as an argument
     * @return ArrayList of students that are associated with the class that is provided as an argument
     */
    public ArrayList<Student> getStudentsByClass(String className) {
        ArrayList<Student> studentsInAClass = new ArrayList<>();
        for (Student student: students) {
            if (Objects.equals(student.getClassName(),className)) {
                studentsInAClass.add(student);
            }
        }
        return studentsInAClass;
    }

}
