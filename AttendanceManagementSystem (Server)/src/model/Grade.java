package model;

public class Grade {
    private int grade;
    private String comment;

    public Grade(){
        this.grade = 0;
        this.comment = null;
    }

    public Grade(int grade, String comment) {
        this.grade = grade;
        this.comment = comment;
    }

    public Grade(int grade){
        this.grade = grade;
        this.comment = null;
    }

    public int getGrade() {
        return grade;
    }

    public String getComment() {
        return comment;
    }

    public Grade copy() {
        return new Grade(this.grade, this.comment);
    }


}

