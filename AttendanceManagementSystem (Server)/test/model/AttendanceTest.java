package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class AttendanceTest {
    private School school;
    private Student student1;
    private Student student2;
    private Lesson lesson;

    @BeforeEach
    void setUp() {
        student1 = new Student("Mac", "333556");
        student2 = new Student("Victor", "123643");


        school = new School();

        // create the class 2x
        school.getClassList().addClass(new Class("2x"));

        // add 2 students in the school student list
        school.getStudentList().addStudent(student1);
        school.getStudentList().addStudent(student2);

        // add the 2 students in the 2x class student list
        school.getClassList().getClassByName("2x").getStudents().addStudent(student1);
        student1.setClassName("2x");
        school.getClassList().getClassByName("2x").getStudents().addStudent(student2);
        student2.setClassName("2x");

        // add a lesson to 2x class' schedule
        lesson = new Lesson(
                "123",
                new Teacher("James Charles", "126656"),
                LocalDate.now(),
                LocalTime.now().minusHours(6),
                LocalTime.now().plusHours(2),
                "SDJ",
                "Threads",
                "Introduction",
                "205A",
                "ex.1",
                "2x"
        );
        school.getClassList().getClassByName("2x").getSchedule().addLesson(lesson);

        // creating the lessonData for the 2 students
        school.getLessonDataList().addLessonData(new LessonData(lesson, student1));
        school.getLessonDataList().addLessonData(new LessonData(lesson, student2));
    }

    @Test
    void setAbsenceStatusTest() {
        // setting the first student as absent.
        school.getLessonDataList().getByStudentAndLesson(lesson, student1).getAbsence().setWasAbsent(true);

        assertTrue(school.getLessonDataList().getByStudentAndLesson(lesson, student1).getAbsence().wasAbsent());
        assertFalse(school.getLessonDataList().getByStudentAndLesson(lesson, student2).getAbsence().wasAbsent());

    }

    @Test
    void setGradeTest() {
        // grading the students.
        school.getLessonDataList().getByStudentAndLesson(lesson, student1).setGrade(new Grade(12));
        school.getLessonDataList().getByStudentAndLesson(lesson, student2).setGrade(new Grade(-3));

        assertEquals(12, school.getLessonDataList().getByStudentAndLesson(lesson, student1).getGrade().getGrade());
        assertEquals(-3, school.getLessonDataList().getByStudentAndLesson(lesson, student2).getGrade().getGrade());

    }

    @Test
    void setGradeAndCommentTest() {
        // grading the students and adding comments.
        school.getLessonDataList().getByStudentAndLesson(lesson, student1).setGrade(new Grade(12, "Great job"));
        school.getLessonDataList().getByStudentAndLesson(lesson, student2).setGrade(new Grade(-3, "Try again next semester"));

        assertEquals(12, school.getLessonDataList().getByStudentAndLesson(lesson, student1).getGrade().getGrade());
        assertEquals("Great job", school.getLessonDataList().getByStudentAndLesson(lesson, student1).getGrade().getComment());


        assertEquals(-3, school.getLessonDataList().getByStudentAndLesson(lesson, student2).getGrade().getGrade());
        assertEquals("Try again next semester", school.getLessonDataList().getByStudentAndLesson(lesson, student2).getGrade().getComment());
    }
}
