package model;

import java.util.ArrayList;
import java.util.Objects;

public class StudentList {
    private ArrayList<Student> students;

    public StudentList() {
        this.students = new ArrayList<>();
    }

    public StudentList(ArrayList<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) throws IllegalArgumentException {
        if (!isUnique(student.getID())) {
            throw new IllegalArgumentException("Student with ID ("+ student.getID() + ") already exists.");
        }
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public void removeStudent(String studentID) {
        students.removeIf(student -> student.getID().equals(studentID));
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }

    public ArrayList<String> getUnassignedStudents() {
        ArrayList<String> unassignedStudents = new ArrayList<>();
        for (Student student: students) {
            if (student.getClassName() == null) {
                unassignedStudents.add(String.format("%s (%S)",student.getName(), student.getID()));
            }
        }
        return unassignedStudents;
    }

    public Student getStudentByID(String id) throws IllegalArgumentException {
        for (Student student: students) {
            if (student.getID().equals(id)) {
                return student;
            }
        }
        throw new IllegalArgumentException("No such student with this id (" + id + ")");
    }

    private boolean isUnique(String studentID) {
        return students.stream().noneMatch(student -> student.getID().equals(studentID));
    }

    public Student getStudentByName(String name) throws IllegalArgumentException {
        for (Student student: students) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        throw new IllegalArgumentException("No such student with this name (" + name + ")");
    }

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
