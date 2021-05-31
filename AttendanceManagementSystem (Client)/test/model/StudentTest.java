package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student("Joe", "987654");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void emptyNameConstructor() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> student = new Student("", "123456")
        );
        assertEquals("Student name cannot be empty", exception.getMessage(), "Student name set to empty string.");
    }

    @Test
    void whiteSpaceNameConstructor() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> student = new Student("    ", "123456")
        );
        assertEquals("Student name cannot be empty", exception.getMessage(), "Student name set to white-space string.");
    }


    @Test
    void emptyIDConstructor() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> student = new Student("Bob", "")
        );
        assertEquals("Student ID cannot be empty", exception.getMessage(), "Student ID set to empty string.");
    }

    @Test
    void whiteSpaceIDConstructor() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> student = new Student("Bob", "   ")
        );
        assertEquals("Student ID cannot be empty", exception.getMessage(), "Student ID set to white-space string.");
    }

    @Test
    void sixDigitsIDConstructor() {
        assertDoesNotThrow(
                () -> student = new Student("Bob", "122556")
        );
    }

    @Test
    void notSixDigitsIDConstructor() {
        // One
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> student = new Student("Bob", "1")
        );
        assertEquals("Student ID must consist of 6 digits.", exception.getMessage(), "Student ID set to 1 digit string.");

        // Many
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> student = new Student("Bob", "223")
        );
        assertEquals("Student ID must consist of 6 digits.", exception.getMessage(), "Student ID set to 3 digits string.");

        exception = assertThrows(
                IllegalArgumentException.class,
                () -> student = new Student("Bob", "312432141")
        );
        assertEquals("Student ID must consist of 6 digits.", exception.getMessage(), "Student ID set to 9 digits string.");


        // Lower boundary
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> student = new Student("Bob", "23453")
        );
        assertEquals("Student ID must consist of 6 digits.", exception.getMessage(), "Student ID set to 5 digits string.");

        // Upper boundary
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> student = new Student("Bob", "1234567")
        );
        assertEquals("Student ID must consist of 6 digits.", exception.getMessage(), "Student ID set to 7 digits string.");
    }

    @Test
    void hasValidID() {
        // tested in notSixDigitsIDConstructor()
    }

    @Test
    void setClassNameNull() {
        student.setClassName(null);
        assertNull(student.getClassName());
    }

    @Test
    void setClassNameEmpty() {
        student.setClassName("");
        assertEquals("", student.getClassName());
    }

    @Test
    void setClassName() {
        student.setClassName("1z");
        assertEquals("1z", student.getClassName());
    }

    @Test
    void clearClassName() {
        student.setClassName("1x");
        student.clearClassName();
        assertNull(student.getClassName());
    }

    @Test
    void testEquals() {
        student = new Student("Bob", "122567");
        student.setClassName("1x");

        Student student2 = new Student("Bob", "122567");
        student2.setClassName("1x");

        assertTrue(student.equals(student2));
    }

    @Test
    void testEqualsDifferentObject() {
        student = new Student("John", "122567");
        student.setClassName("1x");


        assertFalse(student.equals(new Teacher("John", "122567")));
    }

    @Test
    void testEqualsDifferentName() {
        student = new Student("John", "122567");
        student.setClassName("1x");

        Student student2 = new Student("Bob", "122567");
        student2.setClassName("1x");

        assertFalse(student.equals(student2));
    }

    @Test
    void testEqualsDifferentID() {
        student = new Student("John", "122567");
        student.setClassName("1x");

        Student student2 = new Student("John", "222567");
        student2.setClassName("1x");

        assertFalse(student.equals(student2));
    }

    @Test
    void testEqualsDifferentClassName() {
        student = new Student("John", "122567");
        student.setClassName("1x");

        Student student2 = new Student("John", "122567");
        student2.setClassName("2z");

        assertFalse(student.equals(student2));
    }

    @Test
    void testEqualsDifferent() {
        student = new Student("John", "122567");
        student.setClassName("1x");

        Student student2 = new Student("Bob", "332567");
        student2.setClassName("2z");

        assertFalse(student.equals(student2));
    }
}