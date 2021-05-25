package model;

import java.io.Serializable;
import java.util.Arrays;

public class Grade implements Serializable {
    private int grade;
    private String comment;
    private static final int[] possibleGrades = {-3,-1,0,2,4,7,10,12};

    public Grade(){
        this.grade = -1;
        this.comment = null;
    }

    public Grade(int grade, String comment) {
        this(grade);
        this.comment = comment;
    }

    public Grade(int grade) throws IllegalArgumentException {
        if(Arrays.stream(possibleGrades).noneMatch(i -> i == grade)) {
            throw new IllegalArgumentException("Illegal grade passed. Possible grades: -3, 0, 2, 4, 7, 10, 12.");
        }
        this.grade = grade;
        this.comment = null;
    }

    public int getGrade() {
        return grade;
    }

    public String getComment() {
        return comment;
    }


}

