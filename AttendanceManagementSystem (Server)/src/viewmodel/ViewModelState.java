package viewmodel;

public class ViewModelState {
    private String accessLevel;
    private String section;
    private String id;

    public ViewModelState() {
        this.accessLevel = null;
        this.section = null;
        this.id = null;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSection() {
        return section;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void clear() {
        this.id = null;
    }
}
