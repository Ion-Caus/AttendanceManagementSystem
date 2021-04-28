package model;

public class LessonData {
    private Lesson lesson;
    private Grade grade;
    private Absence absence;

    public LessonData(Lesson lesson) {
        this.lesson = lesson;
        this.grade = null;
        this.absence = null;
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


}
