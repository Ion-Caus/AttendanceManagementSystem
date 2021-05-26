package model.packages;

import java.io.Serializable;

public class Package implements Serializable {
    private String ID;

    public Package(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
