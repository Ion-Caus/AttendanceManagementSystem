package viewModel;

public class ViewModelState {
    private String accessLevel;
    private String section;
    private String studentID;
    private String teacherID;
    private String className;// this can either be a class/ student / teacher
    private String lessonID;

    public ViewModelState() {
        this.accessLevel = null;
        this.section = null;
        this.studentID = null;
        this.teacherID = null;
        this.className = null;
        this.lessonID = null;
    }

    public void setStudentID(String id) {
        this.studentID = id;
    }
    public void setTeacherID(String id) {
        this.teacherID = id;
    }
    public void setClassName(String id) {
        this.className = id;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public String getClassName() {
        return className;
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
        this.studentID = null;
        this.teacherID = null;
        this.className = null;
        this.lessonID = null;
    }
}
