package model;

public class LessonData {
    private Lesson lesson;
    private Student student;
    private Grade grade;
    private Absence absence;


    public LessonData(Lesson lesson, Student student) {
        this.lesson = lesson;
        this.grade = null;
        this.absence = null;
        this.student = student;
    }

    public void setAbsence(Absence absence) {
        this.absence = absence.copy();
    }

    public void setGrade(Grade grade) {
        this.grade = grade.copy();
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
}