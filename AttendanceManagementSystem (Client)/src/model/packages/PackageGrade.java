package model.packages;

import java.io.Serializable;

public class PackageGrade extends Package implements Serializable {
    private String lessonID;
    private int grade;
    private String comment;

    public PackageGrade(String ID, String lessonID, int grade, String comment) {
        super(ID);
        this.lessonID = lessonID;
        this.grade = grade;
        this.comment = comment;
    }

    public String getLessonID() {
        return lessonID;
    }

    public int getGrade() {
        return grade;
    }

    public String getComment() {
        return comment;
    }
}
