package model;

import java.io.Serializable;

/**
 * This class holds the attendance details for one student on specific lesson
 */
public class Absence implements Serializable {
    private String motive;
    private boolean wasAbsent;

    /**
     * default constructor that will initialize all parameters to default values
     */
    public Absence(){
        this.wasAbsent = false;
        this.motive = null;
    }

    /**This is a 2 argument constructor that will initialize values using provided arguments, it will be used when student was not present on the lesson
     * @param wasAbsent holds the information of the students attendance
     * @param reason the reasoning for absence
     */
    public Absence(boolean wasAbsent, String reason) {
        this.wasAbsent = wasAbsent;
        this.motive = reason;
    }

    /** 1 argument constructor that is initializing the was absent value using the provided parameter and the motive to the default value
     * @param wasAbsent
     */
    public Absence(boolean wasAbsent) {
        this.wasAbsent = wasAbsent;
        this.motive = null;
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }

    public boolean wasAbsent() {
        return wasAbsent;
    }

    public void setWasAbsent(boolean wasAbsent) {
        this.wasAbsent = wasAbsent;
    }


}
