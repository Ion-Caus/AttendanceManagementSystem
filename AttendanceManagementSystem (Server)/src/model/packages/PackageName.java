package model.packages;

public class PackageName extends Package {
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
