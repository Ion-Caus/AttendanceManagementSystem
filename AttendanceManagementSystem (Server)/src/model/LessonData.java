package model;

public class LessonData {
    private Lesson lesson;
    private Student student;
    private Grade grade;
    private Absence absence;

    public LessonData(Lesson lesson, Student student) {
        this.lesson = lesson;
        // TODO: 5/19/2021 Handle default values, null causes exception 
        this.grade = new Grade(12,"");
        this.absence = new Absence(false);
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