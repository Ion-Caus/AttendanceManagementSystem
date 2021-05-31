package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbsenceTest {
    private Absence absence;

    @BeforeEach
    void setUp() {
        absence = new Absence();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void emptyAbsenceConstructor() {
        absence = new Absence();
        assertFalse(absence.wasAbsent());
        assertNull(absence.getMotive());
    }

    @Test
    void notAbsentConstructor() {
        absence = new Absence(false);
        assertFalse(absence.wasAbsent());
    }

    @Test
    void absentConstructor() {
        absence = new Absence(true);
        assertTrue(absence.wasAbsent());
    }

    @Test
    void absentCommentConstructor() {
        absence = new Absence(true, "Sick");
        assertTrue(absence.wasAbsent());
        assertEquals("Sick", absence.getMotive());
    }

    @Test
    void nullMotiveConstructor() {
        absence = new Absence(false, null);
        assertNull(absence.getMotive());
    }

    @Test
    void emptyCommentConstructor() {
        absence = new Absence(false, "");
        assertEquals("", absence.getMotive());
    }

    @Test
    void whiteSpaceCommentConstructor() {
        absence = new Absence(false, "    ");
        assertEquals("    ", absence.getMotive());
    }

    @Test
    void commentConstructor() {
        absence = new Absence(true, "doctor");
        assertEquals("doctor", absence.getMotive());
    }

}