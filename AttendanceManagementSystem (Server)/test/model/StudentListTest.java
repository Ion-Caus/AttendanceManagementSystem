package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentListTest {
    private StudentList studentList;

    @BeforeEach
    void setUp() {
        studentList = new StudentList();
    }

    @Test
    void addStudentNull() {
        assertThrows(
                NullPointerException.class,
                () -> studentList.addStudent(null)
        );

    }

    @Test
    void addStudentOne() {
        Student student = new Student("Ion", "223344");
        studentList.addStudent(student);
        assertEquals(student, studentList.getAllStudents().get(0));
    }

    @Test
    void addStudentMany() {
        Student student = new Student("Ion", "223344");
        Student student2 = new Student("Max", "243334");

        studentList.addStudent(student);
        studentList.addStudent(student2);

        assertEquals(student, studentList.getAllStudents().get(0));
        assertEquals(student2, studentList.getAllStudents().get(1));
    }

    @Test
    void addStudentBoundary() {
        // [LOWER BOUNDARY] tested in addStudentOne()

        // [UPPER BOUNDARY] no upper boundary
    }

    @Test
    void addStudentExceptionSameID() {
        Student student = new Student("Ion", "223344");
        Student student2 = new Student("Max", "223344");

        studentList.addStudent(student);
        student.setClassName("studentList");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> studentList.addStudent(student2)
        );
        assertEquals("Student with ID (223344) already exists.", exception.getMessage(), "Add student with an existing id.");

        exception = assertThrows(
                IllegalArgumentException.class,
                () -> studentList.addStudent(student)
        );
        assertEquals("Student with ID (223344) already exists.", exception.getMessage(), "Add a student, who is already assigned to a class, to the same class");


        StudentList newStudentList = new StudentList();
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> newStudentList.addStudent(student)
        );
        assertEquals("This student is already in another class.", exception.getMessage(), "Add a student, who is already assigned to a class, to another class");
    }

        @Test
    void removeStudent() {
        studentList.addStudent(new Student("Max", "234654"));
        studentList.removeStudent("234654");
        assertEquals(0, studentList.getAllStudents().size());
    }


    @Test
    void getStudentByID() {
        Student student = new Student("Max", "234654");
        studentList.addStudent(student);
        assertEquals(student, studentList.getStudentByID("234654"));
    }

    @Test
    void getStudentByIDException() {
        String id = "233000";
        studentList.addStudent(new Student("Max", "234654"));
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> studentList.getStudentByID(id)
        );
        assertEquals("No such student with this id (" + id + ")", exception.getMessage());
    }

    @Test
    void getStudentByName() {
        Student student = new Student("Max", "234654");
        studentList.addStudent(student);
        assertEquals(student, studentList.getStudentByName("Max"));
    }

    @Test
    void getStudentByNameException() {
        String name = "Joe";
        studentList.addStudent(new Student("Max", "234654"));
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> studentList.getStudentByName(name)
        );
        assertEquals("No such student with this name (" + name + ")", exception.getMessage());
    }
}