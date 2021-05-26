package model.packages;

import java.io.Serializable;

public class PackageAbsence extends Package implements Serializable {
    private String lessonID;
    private boolean absent;
    private String motive;

    public PackageAbsence(String ID, String lessonID, boolean absent, String motive) {
        super(ID);
        this.lessonID = lessonID;
        this.absent = absent;
        this.motive = motive;
    }

    public PackageAbsence(String ID, String lessonID, String motive) {
        super(ID);
        this.lessonID = lessonID;
        this.absent = false;
        this.motive = motive;
    }

    public PackageAbsence(String ID, String lessonID, boolean absent) {
        super(ID);
        this.lessonID = lessonID;
        this.absent = absent;
        this.motive = null;
    }

    public String getLessonID() {
        return lessonID;
    }

    public String getMotive() {
        return motive;
    }

    public boolean isAbsent() {
        return absent;
    }
}
