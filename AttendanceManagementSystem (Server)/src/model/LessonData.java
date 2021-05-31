package model;

import java.io.Serializable;

/**
 * A class representing LessonData- lesson data is associated ot one specific lesson and one specific student and holds the data like Grade and Absence
 */
public class LessonData implements Serializable {
    private Lesson lesson;
    private Student student;
    private Grade grade;
    private Absence absence;

    /**
     * This is a 2 argument constructor that will initialize the lesson and student using the provided arguments and initialize new Absence and grade
     * @param lesson
     * @param student
     */
    public LessonData(Lesson lesson, Student student) {
        this.lesson = lesson;
        this.grade = new Grade();
        this.absence = new Absence();
        this.student = student;
    }

    public void setAbsence(Absence absence) {
        this.absence = absence;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Grade getGrade() {
        return grade;
    }

    public Absence getAbsence() {
        return absence;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public Student getStudent() {
        return student;
    }

    @Override
    public String toString() {
        return "LessonData{" +
                "lesson=" + lesson.getClassName() +
                ", student=" + student.getName() +
                ", grade=" + grade.getGrade() +
                ", absence=" + absence.isWasAbsent() +
                '}';
    }
}