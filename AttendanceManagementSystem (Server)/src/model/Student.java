package model;

import java.util.Objects;

public class Student {
    private LessonData lessonData;
    private String name;
    private String ID;

    private Account account;

    public Student(String name, String ID) {
        this.name = name;
        this.ID = ID;
        this.lessonData = null;
        this.account = null;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LessonData getLessonData() {
        return lessonData;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Student))
            return false;

        Student student = (Student) obj;
        return Objects.equals(name, student.name) &&
                Objects.equals(lessonData, student.lessonData) &&
                Objects.equals(ID, student.ID) &&
                Objects.equals(account, student.account);
    }
}
