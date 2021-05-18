package viewModel;

public class ViewModelState {
    private String accessLevel;
    private String section;
    private String ID; // this can either be a class/ student / teacher
    private String lessonID;

    public ViewModelState() {
        this.accessLevel = null;
        this.section = null;
        this.ID = null;
        this.lessonID = null;
    }

    public void setID(String id) {
        this.ID = id;
    }

    public String getID() {
        return ID;
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

    public String getLessonID() {
        return lessonID;
    }

    public void setLessonID(String lessonID) {
        this.lessonID = lessonID;
    }

    public void clear() {
        this.section = null;
        this.ID = null;
        this.lessonID = null;
    }
}
