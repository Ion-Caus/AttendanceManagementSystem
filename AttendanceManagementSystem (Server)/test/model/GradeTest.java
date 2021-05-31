package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradeTest {
    private Grade grade;

    @BeforeEach
    void setUp() {
        grade = new Grade();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void emptyGradeConstructor() {
        grade = new Grade();
        assertEquals(-1, grade.getGrade());
        assertNull(grade.getComment());
    }

    @Test
    void notValidGradeConstructor() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> grade = new Grade(-10)
        );
        assertEquals("Illegal grade passed. Possible grades: -3, 0, 2, 4, 7, 10, 12.", exception.getMessage(), "Grade set to -10.");

        exception = assertThrows(
                IllegalArgumentException.class,
                () -> grade = new Grade(-4)
        );
        assertEquals("Illegal grade passed. Possible grades: -3, 0, 2, 4, 7, 10, 12.", exception.getMessage(), "Grade set to -4.");

        exception = assertThrows(
                IllegalArgumentException.class,
                () -> grade = new Grade(1)
        );
        assertEquals("Illegal grade passed. Possible grades: -3, 0, 2, 4, 7, 10, 12.", exception.getMessage(), "Grade set to 1.");


        exception = assertThrows(
                IllegalArgumentException.class,
                () -> grade = new Grade(3)
        );
        assertEquals("Illegal grade passed. Possible grades: -3, 0, 2, 4, 7, 10, 12.", exception.getMessage(), "Grade set to 3.");

        exception = assertThrows(
                IllegalArgumentException.class,
                () -> grade = new Grade(8)
        );
        assertEquals("Illegal grade passed. Possible grades: -3, 0, 2, 4, 7, 10, 12.", exception.getMessage(), "Grade set to 8.");

        exception = assertThrows(
                IllegalArgumentException.class,
                () -> grade = new Grade(11)
        );
        assertEquals("Illegal grade passed. Possible grades: -3, 0, 2, 4, 7, 10, 12.", exception.getMessage(), "Grade set to 11.");

        exception = assertThrows(
                IllegalArgumentException.class,
                () -> grade = new Grade(20)
        );
        assertEquals("Illegal grade passed. Possible grades: -3, 0, 2, 4, 7, 10, 12.", exception.getMessage(), "Grade set to 20.");

    }

    @Test
    void validGradeConstructor() {
        grade = new Grade(-3);
        assertEquals(-3, grade.getGrade());

        grade = new Grade(-1);
        assertEquals(-1, grade.getGrade());

        grade = new Grade(0);
        assertEquals(0, grade.getGrade());

        grade = new Grade(2);
        assertEquals(2, grade.getGrade());

        grade = new Grade(4);
        assertEquals(4, grade.getGrade());

        grade = new Grade(7);
        assertEquals(7, grade.getGrade());

        grade = new Grade(10);
        assertEquals(10, grade.getGrade());

        grade = new Grade(12);
        assertEquals(12, grade.getGrade());
    }

    @Test
    void nullCommentConstructor() {
        grade = new Grade(10, null);
        assertNull(grade.getComment());
    }

    @Test
    void emptyCommentConstructor() {
        grade = new Grade(10, "");
        assertEquals("", grade.getComment());
    }

    @Test
    void whiteSpaceCommentConstructor() {
        grade = new Grade(10, "    ");
        assertEquals("    ", grade.getComment());
    }

    @Test
    void commentConstructor() {
        grade = new Grade(10, "great");
        assertEquals("great", grade.getComment());
    }
}