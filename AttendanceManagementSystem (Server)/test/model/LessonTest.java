package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class LessonTest {
    private Lesson lesson;

    @BeforeEach
    void setUp() {
        lesson = new Lesson(
                new Teacher("Bob", "123456"),
                LocalDate.now(),
                LocalTime.now().minusHours(2),
                LocalTime.now().plusHours(2),
                "Math",
                "Logarithm",
                "Introduction",
                "205A",
                "ex.1",
                "1z"
                );
    }

    @Test
    void setEmptySubject() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> lesson.setSubject("")
        );
        assertEquals("Please fill out the subject", exception.getMessage(), "Subject set to empty");

    }

    @Test
    void setWhiteSpaceSubject() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> lesson.setSubject("   ")
        );
        assertEquals("Please fill out the subject", exception.getMessage(), "Subject set to white-space");

    }

    @Test
    void setEmptyTopic() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> lesson.setTopic("")
        );
        assertEquals("Please fill out the topic", exception.getMessage(), "Topic set to empty");

    }

    @Test
    void setWhiteSpaceTopic() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> lesson.setTopic("   ")
        );
        assertEquals("Please fill out the topic", exception.getMessage(), "Topic set to white-space");

    }

    @Test
    void notValidTimeHour() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> lesson = new Lesson(
                        new Teacher("Bob", "123456"),
                        LocalDate.now(),
                        LocalTime.now().plusHours(1),
                        LocalTime.now(),
                        "Math",
                        "Logarithm",
                        "Introduction",
                        "205A",
                        "ex.1",
                        "1z"
                )
        );
        assertEquals("Illegal lesson time", exception.getMessage(), "Lesson has invalid time");

    }

    @Test
    void notValidTimeMinute() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> lesson = new Lesson(
                        new Teacher("Bob", "123456"),
                        LocalDate.now(),
                        LocalTime.now().plusMinutes(1),
                        LocalTime.now(),
                        "Math",
                        "Logarithm",
                        "Introduction",
                        "205A",
                        "ex.1",
                        "1z"
                )
        );
        assertEquals("Illegal lesson time", exception.getMessage(), "Lesson has invalid time");
    }

    @Test
    void notValidTimeSecunds() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> lesson = new Lesson(
                        new Teacher("Bob", "123456"),
                        LocalDate.now(),
                        LocalTime.now().plusSeconds(1),
                        LocalTime.now(),
                        "Math",
                        "Logarithm",
                        "Introduction",
                        "205A",
                        "ex.1",
                        "1z"
                )
        );
        assertEquals("Illegal lesson time", exception.getMessage(), "Lesson has invalid time");
    }

    @Test
    void validTimeSameTime() {
        assertDoesNotThrow(
                () -> lesson = new Lesson(
                        new Teacher("Bob", "123456"),
                        LocalDate.now(),
                        LocalTime.now(),
                        LocalTime.now(),
                        "Math",
                        "Logarithm",
                        "Introduction",
                        "205A",
                        "ex.1",
                        "1z"
                )
        );
    }

    @Test
    void validTime() {
        assertDoesNotThrow(
                () -> lesson = new Lesson(
                        new Teacher("Bob", "123456"),
                        LocalDate.now(),
                        LocalTime.now(),
                        LocalTime.now().plusHours(1),
                        "Math",
                        "Logarithm",
                        "Introduction",
                        "205A",
                        "ex.1",
                        "1z"
                )
        );
    }
}