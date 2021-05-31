package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {
    private Teacher teacher;

    @BeforeEach
    void setUp() {
        teacher = new Teacher("Joe", "987654");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void emptyNameConstructor() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> teacher = new Teacher("", "123456")
        );
        assertEquals("Teacher name cannot be empty", exception.getMessage(), "Teacher name set to empty string.");
    }

    @Test
    void whiteSpaceNameConstructor() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> teacher = new Teacher("    ", "123456")
        );
        assertEquals("Teacher name cannot be empty", exception.getMessage(), "Teacher name set to white-space string.");
    }


    @Test
    void emptyIDConstructor() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> teacher = new Teacher("Bob", "")
        );
        assertEquals("Teacher ID cannot be empty", exception.getMessage(), "Teacher ID set to empty string.");
    }

    @Test
    void whiteSpaceIDConstructor() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> teacher = new Teacher("Bob", "   ")
        );
        assertEquals("Teacher ID cannot be empty", exception.getMessage(), "Teacher ID set to white-space string.");
    }

    @Test
    void sixDigitsIDConstructor() {
        assertDoesNotThrow(
                () -> teacher = new Teacher("Bob", "122556")
        );
    }

    @Test
    void notSixDigitsIDConstructor() {
        // One
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> teacher = new Teacher("Bob", "1")
        );
        assertEquals("Teacher ID must consist of 6 digits.", exception.getMessage(), "Teacher ID set to 1 digit string.");

        // Many
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> teacher = new Teacher("Bob", "223")
        );
        assertEquals("Teacher ID must consist of 6 digits.", exception.getMessage(), "Teacher ID set to 3 digits string.");

        exception = assertThrows(
                IllegalArgumentException.class,
                () -> teacher = new Teacher("Bob", "312432141")
        );
        assertEquals("Teacher ID must consist of 6 digits.", exception.getMessage(), "Teacher ID set to 9 digits string.");


        // Lower boundary
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> teacher = new Teacher("Bob", "23453")
        );
        assertEquals("Teacher ID must consist of 6 digits.", exception.getMessage(), "Teacher ID set to 5 digits string.");

        // Upper boundary
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> teacher = new Teacher("Bob", "1234567")
        );
        assertEquals("Teacher ID must consist of 6 digits.", exception.getMessage(), "Teacher ID set to 7 digits string.");
    }

    @Test
    void hasValidID() {
        // tested in notSixDigitsIDConstructor()
    }

    @Test
    void getInitials() {
        teacher = new Teacher("Bob", "213456");
        assertEquals("B", teacher.getInitials());

        teacher = new Teacher("James White", "213456");
        assertEquals("JW", teacher.getInitials());

        teacher = new Teacher("Ana Maria Popa ", "213456");
        assertEquals("AMP", teacher.getInitials());
    }

    @Test
    void testEquals() {
        teacher = new Teacher("Bob", "122567");

        Teacher teacher2 = new Teacher("Bob", "122567");

        assertTrue(teacher.equals(teacher2));
    }

    @Test
    void testEqualsDifferentObject() {
        teacher = new Teacher("John", "122567");


        assertFalse(teacher.equals(new Student("John", "122567")));
    }

    @Test
    void testEqualsDifferentName() {
        teacher = new Teacher("John", "122567");

        Teacher teacher2 = new Teacher("Bob", "122567");

        assertFalse(teacher.equals(teacher2));
    }

    @Test
    void testEqualsDifferentID() {
        teacher = new Teacher("John", "122567");

        Teacher teacher2 = new Teacher("John", "222567");

        assertFalse(teacher.equals(teacher2));
    }

    @Test
    void testEqualsDifferent() {
        teacher = new Teacher("John", "122567");

        Teacher teacher2 = new Teacher("Bob", "332567");

        assertFalse(teacher.equals(teacher2));
    }
}