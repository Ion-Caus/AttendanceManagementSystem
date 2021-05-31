package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleTest {
    private Schedule schedule;
    private Lesson lesson;

    @BeforeEach
    void setUp() {
        schedule = new Schedule();
        lesson = new Lesson(
                new Teacher("Bob", "123456"),
                LocalDate.now(),
                LocalTime.of(8,5,0),
                LocalTime.of(10,15,0),
                "Math",
                "Logarithm",
                "Introduction",
                "205A",
                "ex.1",
                "1z"
        );
    }

    @Test
    void addLessonNull() {
        schedule.addLesson(null);
        assertNull(schedule.getAllLessons().get(0));
    }

    @Test
    void addLessonOne() {
        schedule.addLesson(lesson);
        assertEquals(lesson, schedule.getAllLessons().get(0));
    }

    @Test
    void addLessonMany() {
        schedule.addLesson(lesson);
        Lesson lesson2 = new Lesson(
                new Teacher("Joe", "126656"),
                LocalDate.now(),
                LocalTime.of(10,35,0),
                LocalTime.of(12,15,0),
                "SDJ",
                "Threads",
                "Introduction",
                "205A",
                "ex.1",
                "1z"
        );
        schedule.addLesson(lesson2);

        assertEquals(lesson, schedule.getAllLessons().get(0));
        assertEquals(lesson2, schedule.getAllLessons().get(1));
    }

    @Test
    void addLessonBoundary() {
        // [LOWER BOUNDARY] tested in addLessonOne()

        // [UPPER BOUNDARY] no upper boundary
    }

    @Test
    void addLessonException() {
        // No exception
    }

    @Test
    void removeLesson() {
        schedule.addLesson(lesson);
        schedule.removeLesson(lesson);
        assertEquals(0, schedule.getAllLessons().size());
    }

    @Test
    void getLessonByID() {
        Lesson lesson2 = new Lesson(
                "123",
                new Teacher("Joe", "126656"),
                LocalDate.now(),
                LocalTime.of(10,35,0),
                LocalTime.of(12,15,0),
                "SDJ",
                "Threads",
                "Introduction",
                "205A",
                "ex.1",
                "1z"
        );
        schedule.addLesson(lesson2);
        schedule.addLesson(lesson);

        assertEquals(lesson2, schedule.getLessonBy("123"));
    }

    @Test
    void getLessonByIDException() {
        schedule.addLesson(lesson);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> schedule.getLessonBy("123")

        );
        assertEquals("No such lesson with this id (123)", exception.getMessage());

    }

    @Test
    void getLessonByTeacher() {
        schedule.addLesson(lesson);
        Teacher teacher = new Teacher("Joe", "126656");
        Lesson lesson2 = new Lesson(
                "123",
                teacher,
                LocalDate.now(),
                LocalTime.of(12,35,0),
                LocalTime.of(14,15,0),
                "SDJ",
                "Threads",
                "Introduction",
                "205A",
                "ex.1",
                "1z"
        );
        schedule.addLesson(lesson2);

        assertEquals( teacher, schedule.getLessonBy(teacher, LocalDate.now()).get(0).getTeacher());
    }

    @Test
    void getLessonByTeacherEmpty() {
        schedule.addLesson(lesson);
        Lesson lesson2 = new Lesson(
                "123",
                new Teacher("Max", "123555"),
                LocalDate.now(),
                LocalTime.of(12,35,0),
                LocalTime.of(14,15,0),
                "SDJ",
                "Threads",
                "Introduction",
                "205A",
                "ex.1",
                "1z"
        );
        schedule.addLesson(lesson2);

        Teacher teacher = new Teacher("Joe", "126656");
        assertEquals( new ArrayList<Lesson>(), schedule.getLessonBy(teacher, LocalDate.now()));
    }

    @Test
    void getLessonByDate() {
        schedule.addLesson(lesson);
        assertEquals( lesson, schedule.getLessonBy(LocalDate.now()).get(0));
    }

    @Test
    void getLessonByDateEmpty() {
        schedule.addLesson(lesson);
        assertEquals( new ArrayList<Lesson>(), schedule.getLessonBy(LocalDate.now().plusDays(10)));
    }
}