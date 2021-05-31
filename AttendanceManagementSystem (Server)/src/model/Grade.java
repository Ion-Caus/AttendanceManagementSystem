package model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * This class represents a grade that students can get, the grades are based on the danish grading system
 */
public class Grade implements Serializable {
    private int grade;
    private String comment;
    private static final int[] possibleGrades = {-3,-1,0,2,4,7,10,12};

    /**
     * A 0 argument constructor that will initialize the grade to default values
     */
    public Grade(){
        this.grade = -1;
        this.comment = null;
    }

    /**
     * A 2 argument constructor that will initialize the comment to the value provided as argument and call another constructor to which it will provide the grade
     * @param grade this represents grade student received
     * @param comment this represents the comment
     */
    public Grade(int grade, String comment) {
        this(grade);
        this.comment = comment;
    }

    /**
     * A 1 parameter constructor that will validate the grade (according to the danish grading system [-3, 0, 2, 4, 7, 10, 12]) and if it is correct, it will initialize it
     * @param grade this represents the grade student has received
     * @throws IllegalArgumentException in case the grade is not valid, this exceptions will be thrown
     */
    public Grade(int grade) throws IllegalArgumentException {
        if(Arrays.stream(possibleGrades).noneMatch(i -> i == grade)) {
            throw new IllegalArgumentException("Illegal grade passed. Possible grades: -3, 0, 2, 4, 7, 10, 12.");
        }
        this.grade = grade;
        this.comment = null;
    }

    /**
     * @return will return the grade
     */
    public int getGrade() {
        return grade;
    }

    /**
     * @return will return the comment
     */
    public String getComment() {
        return comment;
    }


}

