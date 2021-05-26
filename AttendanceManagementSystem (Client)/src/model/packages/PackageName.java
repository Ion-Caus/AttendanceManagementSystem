package model.packages;

import java.io.Serializable;

public class PackageName extends Package implements Serializable {
    private String name;

    public PackageName(String ID, String name) {
        super(ID);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setClassName(String name) {
        this.name = name;
    }
}
