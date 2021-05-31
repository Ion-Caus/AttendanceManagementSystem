package model;

import java.io.Serializable;

public class Absence implements Serializable {
    private String motive;
    private boolean wasAbsent;

    public Absence(){
        this.wasAbsent = false;
        this.motive = null;
    }

    public Absence(boolean wasAbsent, String reason) {
        this.wasAbsent = wasAbsent;
        this.motive = reason;
    }

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
